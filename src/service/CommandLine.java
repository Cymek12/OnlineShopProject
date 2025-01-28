package service;

import exception.EmptyCartException;
import exception.NotAvailableInStorageException;
import exception.WrongIdException;
import model.*;
import model.utils.*;

import java.util.*;

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
            System.out.println("3. Przejdź do koszyka");
            System.out.println("4. Zamknij program");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> productManager.printProducts();
                case 2 -> addProductToCart();
                case 3 -> {
                    try {
                        cart();
                    } catch (EmptyCartException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
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
                cart.addProductToCart(productOpt.get());
            } catch (NotAvailableInStorageException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println("Produkt o podanym Id nie istnieje");
        }
    }


    private void cart() throws EmptyCartException {
        boolean isRunning = true;
        while (isRunning){
            System.out.println("Koszyk:");
            cart.printAddedProducts();
            System.out.println("\nDo zapłaty: " + (Math.round(cart.getOrderPrice() * 100.0) / 100.0) + "zł");

            System.out.println("1. Finalizuj zamówienie");
            System.out.println("2. Skonfiguruj produkt w koszyku");
            System.out.println("3. Usuń wszystkie produkty z koszyka");
            System.out.println("4. Usuń produkt z koszyka");
            System.out.println("5. Cofnij");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1 -> finalizeOrder();
                case 2 -> configureProduct();
                case 3 -> {
                    cart.clearAddedProducts();
                    System.out.println("Usunięto produkty z koszyka");
                    mainMenu();
                }
                case 4 -> {
                    deleteProductFromCart();
                    if(cart.isCartEmpty()){
                        mainMenu();
                    }
                }
                case 5 -> isRunning = false;
                default -> System.out.println("Wybrano niepoprawną opcję");
            }
        }
    }

    private void finalizeOrder() throws EmptyCartException {
        for (Product addedProduct : cart.getAddedProducts()) {
            if(addedProduct.getType().equals("Computer") || addedProduct.getType().equals("Smartphone")){
                if(addedProduct.getChosenConfiguration().isEmpty()){
                    System.out.println("Proszę najpierw skonfigurować produkty");
                    return;
                }
            }
        }
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


    private void configureProduct() throws EmptyCartException {
        System.out.println("Koszyk:");
        cart.printAddedProducts();
        System.out.println("\nPodaj id produktu który chcesz skonfigurować:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<Product> optAddedProduct = cart.findAddedProductById(id);
        if(optAddedProduct.isPresent()){
            if(optAddedProduct.get().getType().equals("Computer")) {
                configureComputer(optAddedProduct.get());
            }
            else if(optAddedProduct.get().getType().equals("Smartphone")){
                configureSmartphone(optAddedProduct.get());
            }
            else {
                System.out.println("Produkt o podanym Id nie podlega konfiguracji");
            }
        }
        else {
            System.out.println("Produkt o podanym Id nie znajduje się w koszyku");
        }
    }


    private void configureComputer(Product product){
        List<ProductConfiguration> chosenConfiguration = new ArrayList<>();
        System.out.println("Konfiguracja komputera:");
        try {
            System.out.println("Wybierz processor: ");
            Processor.printProcessors();
            Processor processor = Processor.findProcessorById(scanner.nextInt());
            System.out.println("Wybierz ilość RAM:");
            RamSize.printRamSizes();
            RamSize ramSize = RamSize.findRamSizeById(scanner.nextInt());
            System.out.println("Wybierz kartę graficzną:");
            GraphicsCard.printGraphicsCards();
            GraphicsCard graphicsCard = GraphicsCard.findGraphicsCardById(scanner.nextInt());
            System.out.println("Wybierz pojemność dysku twardego:");
            StorageSize.printStorageSizes();
            StorageSize storageSize = StorageSize.findStorageSizeById(scanner.nextInt());

            chosenConfiguration.add(new ProductConfiguration<>(processor));
            chosenConfiguration.add(new ProductConfiguration<>(ramSize));
            chosenConfiguration.add(new ProductConfiguration<>(graphicsCard));
            chosenConfiguration.add(new ProductConfiguration<>(storageSize));

        } catch (WrongIdException e) {
            throw new RuntimeException(e);
        }

        cart.configureComputer(product, chosenConfiguration);
    }

    private void configureSmartphone(Product product){
        List<ProductConfiguration> chosenConfiguration = new ArrayList<>();
        List<ProductConfiguration> chosenAccessories;
        System.out.println("Konfiguracja telefonu:");
        try {
            System.out.println("Wybierz kolor:");
            Color.printColors();
            Color color = Color.findColorById(scanner.nextInt());
            System.out.println("Wybierz pojemność baterii:");
            BatteryCapacity.printBatteryCapacities();
            BatteryCapacity batteryCapacity = BatteryCapacity.findBatteryCapacityById(scanner.nextInt());

            chosenAccessories = getChosenAccessories();
            chosenConfiguration.add(new ProductConfiguration<>(color));
            chosenConfiguration.add(new ProductConfiguration<>(batteryCapacity));

        } catch (WrongIdException e) {
            throw new RuntimeException(e);
        }

        cart.configureSmartphone(product, chosenConfiguration, chosenAccessories);
    }

    private List<ProductConfiguration> getChosenAccessories() throws WrongIdException {
        List<ProductConfiguration> resultList = new ArrayList<>();
        System.out.println("Czy chcesz dodać akcesoria do zamówienia?");
        System.out.println("1. Tak");
        System.out.println("2. Nie");
        int addAccessoriesOption = scanner.nextInt();
        if(addAccessoriesOption  == 2){
            return resultList;
        }

        while (true) {
            System.out.println("Wybierz akcesoria:");
            Accessories.printAccessories();
            Accessories accessories = Accessories.findAccessoriesById(scanner.nextInt());
            resultList.add(new ProductConfiguration<>(accessories));

            System.out.println("Czy chcesz dodać kolejne akcesorium?");
            System.out.println("1. Tak");
            System.out.println("2. Nie");
            int accessoryOption = scanner.nextInt();
            if (accessoryOption == 2) {
                break;
            }
        }
        return resultList;
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
