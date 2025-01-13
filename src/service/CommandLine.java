package service;

import exception.NotAvailableInStorageException;
import model.Product;

import java.util.Optional;
import java.util.Scanner;

public class CommandLine {
    Scanner scanner = new Scanner(System.in);
    ProductManager productManager = new ProductManager();
    Cart cart = new Cart();



    public void run(){
        System.out.println(" ---> Sklep Internetowy <---");
        mainMenu();
    }

    private void mainMenu(){
        boolean isRunning = true;


        while (isRunning){
            System.out.println("Wybierz jedną z opcji:");
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
                case 3 -> configureProductInCartMenu();
                case 4 -> printCart();
                case 5 -> isRunning = false;
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
                System.out.println("Dodano produkt do koszyka");
            } catch (NotAvailableInStorageException e) {
                System.out.println("!Wybrany produkt nie jest dostępny w magazynie!");
            }
        }
        else {
            System.out.println("Produkt o podanym Id nie istnieje");
        }
    }

    private void configureProductInCartMenu(){

    }

    private void printCart() {

    }
}
