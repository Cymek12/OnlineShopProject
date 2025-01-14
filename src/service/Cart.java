package service;

import exception.NotAvailableInStorageException;
import model.Computer;
import model.Product;
import model.Smartphone;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Cart {
    private List<Product> addedProducts = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void addProductToCart(Product product) throws NotAvailableInStorageException {
        if(product.getAvailableQuantity() == 0){
            throw new NotAvailableInStorageException("Product is not available at the moment");
        }

        addedProducts.add(product);
        product.setAvailableQuantity(product.getAvailableQuantity() - 1);
        System.out.println("Dodano do koszyka: " + product);

    }

    public void printAddedProducts(){
        if(addedProducts.isEmpty()){
            System.out.println("Koszyk jest pusty");
            return;
        }
        for (Product addedProduct : addedProducts) {
            System.out.println(addedProduct);
        }
    }


    public boolean deleteProductFromCart(Product product){
        if(product == null){
            throw new NullPointerException("Product cannot be null");
        }
        if(addedProducts.remove(product)){
            product.setAvailableQuantity(product.getAvailableQuantity() + 1);
            return true;
        }
        return false;
    }

    public List<Product> getAddedProducts() {
        if(addedProducts.isEmpty()){
            //throw new EmptyCartException("Cart is empty");
        }
        return addedProducts;
    }

    public double getOrderPrice() {
        return addedProducts.stream().mapToDouble(Product::getPrice).sum();
    }

    public void clearAddedProducts(){
        addedProducts.clear();
    }

    public void deleteProductFromList(int id){
        Optional<Product> optAddedProduct = addedProducts.stream().filter(product -> product.getId() == id).findFirst();
        if(optAddedProduct.isPresent()){
            addedProducts.remove(optAddedProduct.get());
            System.out.println("Usunięto produkt z koszyka");
        }
        else {
            System.out.println("Produkt o podanym Id nie znajduje się w koszyku");
        }
    }

    public void configureProduct(int id) {
        Optional<Product> optAddedProduct = addedProducts.stream().filter(product -> product.getId() == id).findFirst();
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

    public void configureComputer(Computer computer){
        System.out.println("Konfiguracja komputera:");
        System.out.println("Wybierz processor: (intel i3, intel i5, intel i7, intel i9, amd ryzen 5, amd ryzen 7) ");
        computer.setProcessor(scanner.nextLine());
        System.out.println("Wybierz ilość RAM: (4, 8, 16, 32, 64, 128) ");
        computer.setRamSize(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Wybierz kartę graficzną: (RTX 780, RTX 4060, RTX 6000, RTX 3070");
        computer.setGraphicsCard(scanner.nextLine());
        System.out.println("Wybierz pojemność dysku twardego: (128, 256, 512, 1000, 2000, 5000");
        computer.setStorageSize(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Zmieniono konfiguracje komputera!");
    }

    public void configureSmartphone(Smartphone smartphone){
        System.out.println("Konfiguracja telefonu:");
        System.out.println("Wybierz kolor: (Czarny, Biały, Czerwony, Niebieski, Zielony) ");
        smartphone.setColor(scanner.nextLine());
        System.out.println("Wybierz pojemność baterii (2800, 3500, 4000, 4800)");
        smartphone.setBatteryCapacity(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Wybierz akcesoria do telefonu (Szybka ładowarka, Etui, Szkło, Słuchawki) ");
        smartphone.setAccessories(scanner.nextLine());

        System.out.println("Zmieniono konfiguracje telefonu!");
    }

}
