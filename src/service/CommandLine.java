package service;

import exception.EmptyCartException;
import exception.NotAvailableInStorageException;
import model.Computer;
import model.Order;
import model.Product;
import model.Smartphone;

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
                case 1 -> productListMenu();
                case 2 -> addProductToCartMenu();
                case 3 -> {
                    try {
                        configureProduct();
                    } catch (EmptyCartException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        cartMenu();
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

    private void productListMenu(){
        productManager.printProducts();
        System.out.println("\nNaciśnij \"Enter\" aby cofnąc się do głównego menu");
        scanner.nextLine();
    }

    private void addProductToCartMenu(){
        productManager.printProducts();
        System.out.println("\nPodaj Id produktu który chcesz dodać");
        int pickedId = scanner.nextInt();

        Optional<Product> productOpt = productManager.findProductById(pickedId);
        if(productOpt.isPresent()){
            try {
                cart.addProductToCart(productOpt.get());
            } catch (NotAvailableInStorageException e) {
                System.out.println("!Wybrany produkt nie jest dostępny w magazynie!");
            }
        }
        else {
            System.out.println("Produkt o podanym Id nie istnieje");
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
        System.out.println("Wybierz processor: (intel i3, intel i5, intel i7, intel i9, amd ryzen 5, amd ryzen 7) ");
        String processor = scanner.nextLine();
        System.out.println("Wybierz ilość RAM: (4, 8, 16, 32, 64, 128) ");
        int ramSize = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Wybierz kartę graficzną: (RTX 780, RTX 4060, RTX 6000, RTX 3070");
        String graphicsCard = scanner.nextLine();
        System.out.println("Wybierz pojemność dysku twardego: (128, 256, 512, 1000, 2000, 5000");
        int storageSize = scanner.nextInt();
        scanner.nextLine();

        cart.configureComputer(computer, processor, ramSize, graphicsCard, storageSize);
    }

    private void configureSmartphone(Smartphone smartphone){
        System.out.println("Konfiguracja telefonu:");
        System.out.println("Wybierz kolor: (Czarny, Biały, Czerwony, Niebieski, Zielony) ");
        String color = scanner.nextLine();
        System.out.println("Wybierz pojemność baterii (2800, 3500, 4000, 4800)");
        int batteryCapacity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Wybierz akcesoria do telefonu (Szybka ładowarka, Etui, Szkło, Słuchawki) ");
        String accessories = scanner.nextLine();

        cart.configureSmartphone(smartphone, color, batteryCapacity, accessories);
    }


    private void cartMenu() throws EmptyCartException {
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
