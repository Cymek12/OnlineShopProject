package service;

import exception.ConfigurationDoesNotExistException;
import exception.EmptyCartException;
import exception.NotAvailableInStorageException;
import model.*;

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

        scanner.close();
    }

    /**
     *  menu głowne wyświetlane użytkownikowi
     */
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
                case 2 -> {
                    try {
                        processAddProductToCart();
                    } catch (ConfigurationDoesNotExistException | NotAvailableInStorageException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        cartOperations();
                    } catch (EmptyCartException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.println("Zamykam aplikacje");
                    isRunning = false;
                }
                default -> System.out.println("Wybrano niepoprawną opcję");
            }
        }
    }

    /**
     * metoda odpowiada za dodanie produktu z magazynu do koszyka
     */
    private void processAddProductToCart() throws ConfigurationDoesNotExistException, NotAvailableInStorageException {
        productManager.printProducts();
        System.out.println("\nPodaj Id produktu który chcesz dodać");
        int pickedId = scanner.nextInt();
        scanner.nextLine();
        Optional<Product> productOpt = productManager.findProductById(pickedId);
        if(productOpt.isEmpty()){
            System.out.println("Produkt o podanym Id nie istnieje");
            return;
        }
        Product product = productOpt.get();

        if(product.getType().equals(ProductType.ELECTRONICS)){
            CartItem cartItem = new CartItem(product, new ArrayList<>());
            cart.addProductToCart(cartItem);
        }
        else {
            CartItem cartItem = configureProductSpecifications(product);
            cart.addProductToCart(cartItem);
        }
    }

    /**
     * menu pozwalające użytkownikwi operacje na swoim koszyku
     */
    private void cartOperations() throws EmptyCartException {
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
                case 1 -> {
                    finalizeOrder();
                    isRunning = false;
                }
                case 2 -> {
                    cart.clearAddedProducts();
                    System.out.println("Usunięto produkty z koszyka");
                    isRunning = false;
                }
                case 3 -> {
                    deleteProductFromCart();
                    if(cart.isCartEmpty()){
                        isRunning = false;
                    }
                }
                case 4 -> isRunning = false;
                default -> System.out.println("Wybrano niepoprawną opcję");
            }
        }
    }

    /**
     * pozwala wpisac dane do realizacji zamowienia
     */
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
        orderProcessor.generateInvoice(order, cart);
        orderProcessor.writeOrderToFile(cart);
        orderProcessor.writeUserToFile(order);
        cart.clearAddedProducts();
    }

    /**
     * pozwala uzytkownikowi wybrac konfiguracje produktu
     */
    private CartItem configureProductSpecifications(Product product) throws ConfigurationDoesNotExistException {
        List<ProductConfiguration> chosenConfiguration = new ArrayList<>();

        System.out.println("Skonfiguruj " + product.getType());

        Map<ConfigurationType, List<ProductConfiguration>> groupedConfigurations = new HashMap<>();
        for (ProductConfiguration availableConfiguration : product.getAvailableConfigurations()) {
            ConfigurationType type = availableConfiguration.getType();
            if(!groupedConfigurations.containsKey(type)){
                groupedConfigurations.put(type, new ArrayList<>());
            }
            groupedConfigurations.get(type).add(availableConfiguration);
        }

        for (ConfigurationType configurationType : groupedConfigurations.keySet()) {
            if(configurationType.equals(ConfigurationType.ACCESSORY)){
                processProductAccessories(product, chosenConfiguration);
            }
            else{
                System.out.println("Wybierz " + configurationType);
                printAvailableConfiguration(product, configurationType);
                ProductConfiguration chosenConfigurationType = getConfigurationType(product, scanner.nextLine());
                chosenConfiguration.add(chosenConfigurationType);
            }
        }
        return new CartItem(product, chosenConfiguration);
    }

    /**
     * pozwala uzytkownikowi wybrac akcesoria do telefonu
     */
    private void processProductAccessories(Product product, List<ProductConfiguration> chosenConfiguration) throws ConfigurationDoesNotExistException {
        System.out.println("Czy chcesz dodać akcesoria?");
        System.out.println("1 - Tak");
        System.out.println("Inna liczba - Nie");
        int addAccessoryOption = scanner.nextInt();
        scanner.nextLine();
        if(addAccessoryOption != 1){
            return;
        }
        while (true){
            System.out.println("Wybierz akcesoria:");
            printAvailableConfiguration(product, ConfigurationType.ACCESSORY);
            ProductConfiguration accessoryType = getConfigurationType(product, scanner.nextLine());
            chosenConfiguration.add(accessoryType);

            System.out.println("Czy chcesz dodać kolejne akcesorium?");
            System.out.println("1 - Tak");
            System.out.println("Inna liczba - Nie");
            int exitOption = scanner.nextInt();
            scanner.nextLine();
            if(exitOption != 1){
                break;
            }
        }
    }

    private ProductConfiguration getConfigurationType(Product product, String chosenOption) throws ConfigurationDoesNotExistException {
        for (ProductConfiguration availableConfiguration : product.getAvailableConfigurations()) {
            if(availableConfiguration.getValue().equals(chosenOption)){
                return availableConfiguration;
            }
        }
        throw new ConfigurationDoesNotExistException("Wybrana konfiguracja nie istnieje");
    }

    private void printAvailableConfiguration(Product product, ConfigurationType configurationType){
        product.getAvailableConfigurations().stream().filter(s -> s.getType().equals(configurationType)).forEach(System.out::println);
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
