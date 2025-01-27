package service;

import exception.EmptyCartException;
import exception.NotAvailableInStorageException;
import model.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


/**
 * Klasa obsługuje wyświetlanie aplikacji w konsoli systemowej
 */
public class CommandLine {
    private Scanner scanner = new Scanner(System.in);
    private ProductManager productManager;
    private Cart cart = new Cart();
    private OrderProcessor orderProcessor = new OrderProcessor();

    public CommandLine(ProductManager productManager) {
        this.productManager = productManager;
    }

    public void run(){
        System.out.println(" ---> Sklep Internetowy <---");
        mainMenu();
    }

    private void mainMenu(){
        boolean isRunning = true;
        while (isRunning){
            System.out.println("1. Wyświetl listę produktów");
            System.out.println("2. Dodaj produkt do koszyka");
            System.out.println("3. Skonfiguruj produkt w koszyku");
            System.out.println("4. Przejdź do koszyka");
            System.out.println("5. Zamknij program");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> productManager.printProducts();
                case 2 -> addProductToCart();
                case 3 -> {
                    try {
                        configureProduct();
                    } catch (EmptyCartException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        cart();
                    } catch (EmptyCartException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 5 -> {
                    System.out.println("Zamykam aplikacje");
                    isRunning = false;
                    scanner.close();
                }
                default -> System.out.println("Wybrano niepoprawną opcję");
            }
        }
    }

    private void addProductToCart(){
        productManager.printProducts();
        System.out.println("\nPodaj Id produktu który chcesz dodać");
        int pickedId = scanner.nextInt();

        Optional<Product> productOpt = productManager.findProductById(pickedId);
        if(productOpt.isPresent()){
            try {
                Product productCopy = createProductCopy(productOpt.get());
                cart.addProductToCart(productCopy);
            } catch (NotAvailableInStorageException e) {
                System.out.println("!Wybrany produkt nie jest dostępny w magazynie!");
            }
        }
        else {
            System.out.println("Produkt o podanym Id nie istnieje");
        }
    }

    private Product createProductCopy(Product product){
        if(product instanceof Computer originalComputer){
            return new Computer(
                    originalComputer.getId(),
                    originalComputer.getName(),
                    originalComputer.getBasePrice(),
                    originalComputer.getAvailableQuantity(),
                    originalComputer.getConfigurations(),
                    originalComputer.getProcessor(),
                    originalComputer.getRamSize(),
                    originalComputer.getGraphicsCard(),
                    originalComputer.getStorageSize()
            );
        }
        else if (product instanceof Smartphone originalSmartphone){
            return new Smartphone(
                    originalSmartphone.getId(),
                    originalSmartphone.getName(),
                    originalSmartphone.getBasePrice(),
                    originalSmartphone.getAvailableQuantity(),
                    originalSmartphone.getConfigurations(),
                    originalSmartphone.getColor(),
                    originalSmartphone.getBatteryCapacity(),
                    originalSmartphone.getAccessories()
            );
        }
        else {
            return new Product(
                    product.getId(),
                    product.getName(),
                    product.getBasePrice(),
                    product.getAvailableQuantity(),
                    product.getConfigurations()
            );
        }
    }

    private void configureProduct() throws EmptyCartException {
        System.out.println("Koszyk:");
        cart.printAddedProducts();
        System.out.println("\nPodaj id produktu którego parametry chcesz edytować:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<Product> optAddedProduct = cart.findAddedProductById(id);
        if(optAddedProduct.isPresent()){
            if(optAddedProduct.get() instanceof Computer computer){
                configureComputer(computer);
            }
            else if(optAddedProduct.get() instanceof Smartphone smartphone){
                configureSmartphone(smartphone);
            }
            else {
                System.out.println("Produkt o podanym Id nie podlega konfiguracji");
            }
        }
        else {
            System.out.println("Produkt o podanym Id nie znajduje się w koszyku");
        }
    }


    private void configureComputer(Computer computer){
        System.out.println("Konfiguracja komputera:");
        System.out.println("Wybierz processor: ");
        String processor = getConfigurationChoice(computer, "processor");
        System.out.println("Wybierz ilość RAM:");
        int ramSize = Integer.parseInt(getConfigurationChoice(computer, "ramSize"));
        System.out.println("Wybierz kartę graficzną:");
        String graphicsCard = getConfigurationChoice(computer, "graphicsCard");
        System.out.println("Wybierz pojemność dysku twardego:");
        int storageSize = Integer.parseInt(getConfigurationChoice(computer, "storageSize"));

        cart.configureComputer(computer, processor, ramSize, graphicsCard, storageSize);
    }

    private void configureSmartphone(Smartphone smartphone){
        System.out.println("Konfiguracja telefonu:");
        System.out.println("Wybierz kolor:");
        String color = getConfigurationChoice(smartphone, "color");
        System.out.println("Wybierz pojemność baterii:");
        int batteryCapacity = Integer.parseInt(getConfigurationChoice(smartphone, "batteryCapacity"));
        System.out.println("Wybierz akcesoria do telefonu: (Wpisuj po przecinku)");
        smartphone.getAccessories().forEach(s -> System.out.println(s.getValue() + " - " + s.getAdditionalPrice() + "zł"));
        List<String> accessories = List.of(scanner.nextLine().split(","));

        List<ProductConfiguration> configuredAccessories = smartphone.getAccessories().stream().filter(s -> accessories.contains(s.getValue())).toList();

        cart.configureSmartphone(smartphone, color, batteryCapacity, configuredAccessories);
    }

    private String getConfigurationChoice(Product product, String type){
        product.getConfigurations().stream()
                .filter(s -> s.getType().equals(type))
                .forEach(s -> System.out.println(s.getValue() + " - " + s.getAdditionalPrice() + "zł"));
        return scanner.nextLine();
    }


    private void cart() throws EmptyCartException {
        boolean isRunning = true;
        while (isRunning){
            System.out.println("Koszyk:");
            cart.printAddedProducts();
            System.out.println("\nDo zapłaty: " + (Math.round(cart.getOrderPrice() * 100.0) / 100.0) + "zł");

            System.out.println("1. Finalizuj zamówienie");
            System.out.println("2. Usuń wszystkie produkty z koszyka");
            System.out.println("3. Usuń produkt z koszyka");
            System.out.println("4. Cofnij");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1 -> finalizeOrder();
                case 2 -> {
                    cart.clearAddedProducts();
                    System.out.println("Usunięto produkty z koszyka");
                    mainMenu();
                }
                case 3 -> {
                    deleteProductFromCart();
                    if(cart.isCartEmpty()){
                        mainMenu();
                    }
                }
                case 4 -> isRunning = false;
                default -> System.out.println("Wybrano niepoprawną opcję");
            }
        }
    }

    private void finalizeOrder(){
        System.out.println("Podaj dane do faktury i wysyłki:");
        System.out.print("Imię: ");
        String firstName = scanner.nextLine();
        System.out.print("Nazwisko: ");
        String lastName = scanner.nextLine();
        System.out.print("Numer telefonu: ");
        int phoneNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Adres email: ");
        String emailAddress = scanner.nextLine();
        System.out.print("Adres do wysyłki: ");
        String deliveryAddress = scanner.nextLine();
        System.out.println("Kod zniżkowy: (jeżeli nie posiadasz kodu zniżkowego, to zostaw pole puste)");
        String discountCode = scanner.nextLine();
        DiscountManager discountManager = new DiscountManager();
        double discount = discountManager.applyDiscount(discountCode);

        Order order = new Order(firstName, lastName, phoneNumber, emailAddress, deliveryAddress, cart.getOrderPrice() * discount);
        Cart cartForGenerateFacture = cart;
        orderProcessor.generateFacture(order, cartForGenerateFacture);
        orderProcessor.writeOrderToFile(cartForGenerateFacture);
        orderProcessor.writeUserToFile(order);
        cart.clearAddedProducts();

    }

    private void deleteProductFromCart() throws EmptyCartException {
        System.out.println("Koszyk:");
        cart.printAddedProducts();
        System.out.println("\nPodaj id produktu który chcesz usunąć:");
        int id = scanner.nextInt();
        scanner.nextLine();
        cart.deleteProductFromCart(id);

    }
}
