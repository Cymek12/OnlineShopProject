package service;

import exception.NotAvailableInStorageException;
import model.Order;
import model.Product;

import java.util.Optional;
import java.util.Scanner;

public class CommandLine {
    Scanner scanner = new Scanner(System.in);
    ProductManager productManager = new ProductManager();
    Cart cart = new Cart();
    OrderProcessor orderProcessor = new OrderProcessor();


    public void run(){
        productManager.exampleData();
        System.out.println(" ---> Sklep Internetowy <---");
        mainMenu();
    }

    private void mainMenu(){
        while (true){
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
                case 3 -> configureAddedProduct();
                case 4 -> cartMenu();
                case 5 -> exit();
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

    private void cartMenu() {
        while (true){
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
                case 2 -> cart.clearAddedProducts();
                case 3 -> deleteProductFromList();
                case 4 -> mainMenu();
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

        orderProcessor.generateFacture(new Order(firstName, lastName, phoneNumber, emailAddress, deliveryAddress, cart.getOrderPrice()), cart);
        mainMenu();
    }

    private void deleteProductFromList(){
        System.out.println("Koszyk:");
        cart.printAddedProducts();
        System.out.println("\nPodaj id produktu który chcesz usunąć:");
        int id = scanner.nextInt();
        scanner.nextLine();
        cart.deleteProductFromList(id);

    }

    private void configureAddedProduct(){
        System.out.println("Koszyk:");
        cart.printAddedProducts();
        System.out.println("\nPodaj id produktu którego parametry chcesz edytować:");
        int id = scanner.nextInt();
        scanner.nextLine();
        cart.configureProduct(id);
    }

    private void exit(){
        System.out.println("Zamykam aplikacje");
        scanner.close();
    }
}
