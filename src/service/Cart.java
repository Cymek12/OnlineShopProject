package service;

import exception.EmptyCartException;
import exception.NotAvailableInStorageException;
import model.Computer;
import model.Product;
import model.Smartphone;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    private List<Product> addedProducts = new ArrayList<>();

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


    public List<Product> getAddedProducts() throws EmptyCartException {
        if(addedProducts.isEmpty()){
            throw new EmptyCartException("Cart is empty");
        }
        return addedProducts;
    }

    public double getOrderPrice() {
        return addedProducts.stream().mapToDouble(Product::getPrice).sum();
    }

    public void clearAddedProducts(){
        addedProducts.clear();
    }

    public Optional<Product> findAddedProductById(int id){
        return addedProducts.stream().filter(product -> product.getId() == id).findFirst();
    }

    public void deleteProductFromCart(int id){
        Optional<Product> optAddedProduct = addedProducts.stream().filter(product -> product.getId() == id).findFirst();
        if(optAddedProduct.isPresent()){
            addedProducts.remove(optAddedProduct.get());
            optAddedProduct.get().setAvailableQuantity(optAddedProduct.get().getAvailableQuantity() + 1);
            System.out.println("Usunięto produkt z koszyka");
        }
        else {
            System.out.println("Produkt o podanym Id nie znajduje się w koszyku");
        }
    }


    public void configureComputer(Computer computer, String processor, int ramSize, String graphicsCard, int storageSize){
        if(processor == null && graphicsCard == null){
            System.out.println("Proszę poprawnie określić parametry komputera");
            return;
        }
        computer.setProcessor(processor);
        computer.setRamSize(ramSize);
        computer.setGraphicsCard(graphicsCard);
        computer.setStorageSize(storageSize);

        System.out.println("Zmieniono konfiguracje komputera!");
    }

    public void configureSmartphone(Smartphone smartphone, String color, int batteryCapacity, String accessories){
        if(color == null && accessories == null){
            System.out.println("Proszę poprawnie określić parametry telefonu");
            return;
        }
        smartphone.setColor(color);
        smartphone.setBatteryCapacity(batteryCapacity);
        smartphone.setAccessories(accessories);

        System.out.println("Zmieniono konfiguracje telefonu!");
    }

}
