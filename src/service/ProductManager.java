package service;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Klasa zawiera liste dostepnych produktow w sklepie oraz metody umozliwiajace podstawowe operacje na liscie. Dodatkowo zaiwera metode implementujaca dane testowe do sklepu.
 */
public class ProductManager {
    private List<Product> products;


    public ProductManager() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product){
        if(product != null){
            products.add(product);
        }
    }

    public void updateProduct(Product updatedProduct){
        if(updatedProduct != null){
            for (Product product : products) {
                if(product.getId() == updatedProduct.getId()){
                    product = updatedProduct;
                }
            }
        }
    }

    public boolean deleteProduct(Product product){
        if(product != null){
            products.remove(product);
        }
        return false;
    }

    public void printProducts(){
        System.out.println("Lista produktów:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public Optional<Product> findProductById(int id){
        return products.stream().filter(product -> product.getId() == id).findFirst();
    }

    public void insertExampleData(){

        List<ProductConfiguration> computerConfigurations = new ArrayList<>();
        computerConfigurations.add(new ProductConfiguration("processor", "Intel i5", 0.00));
        computerConfigurations.add(new ProductConfiguration("processor", "Intel i7", 800.00));
        computerConfigurations.add(new ProductConfiguration("processor", "Intel i9", 1200.00));
        computerConfigurations.add(new ProductConfiguration("ramSize", "8", 0.00));
        computerConfigurations.add(new ProductConfiguration("ramSize", "16", 500.00));
        computerConfigurations.add(new ProductConfiguration("ramSize", "32", 1000.00));
        computerConfigurations.add(new ProductConfiguration("graphicsCard", "NVIDIA GTX 1080", 0.00));
        computerConfigurations.add(new ProductConfiguration("graphicsCard", "NVIDIA RTX 3060", 1200.00));
        computerConfigurations.add(new ProductConfiguration("graphicsCard", "NVIDIA RTX 4070", 2700.00));
        computerConfigurations.add(new ProductConfiguration("storageSize", "256", 0.00));
        computerConfigurations.add(new ProductConfiguration("storageSize", "512", 300.00));
        computerConfigurations.add(new ProductConfiguration("storageSize", "1000", 500.00));

        List<ProductConfiguration> smartphoneConfigurations = new ArrayList<>();
        smartphoneConfigurations.add(new ProductConfiguration("color", "Czarny", 0.00));
        smartphoneConfigurations.add(new ProductConfiguration("color", "Niebieski", 150.00));
        smartphoneConfigurations.add(new ProductConfiguration("color", "Zielony", 150.00));
        smartphoneConfigurations.add(new ProductConfiguration("batteryCapacity", "2200", 0.00));
        smartphoneConfigurations.add(new ProductConfiguration("batteryCapacity", "3200", 400.00));
        smartphoneConfigurations.add(new ProductConfiguration("batteryCapacity", "4200", 800.00));

        List<ProductConfiguration> smartphoneAccessories = new ArrayList<>();
        smartphoneAccessories.add(new ProductConfiguration("accessories", "Słuchawki", 150));
        smartphoneAccessories.add(new ProductConfiguration("accessories", "Szybka ładowarka", 50));
        smartphoneAccessories.add(new ProductConfiguration("accessories", "Etui", 30));
        smartphoneAccessories.add(new ProductConfiguration("accessories", "Uchwyt", 45));

        products.add(new Computer(1, "Gaming PC", 3000.00, 10, computerConfigurations, "Intel i5", 8, "NVIDIA GTX 1080", 256));
        products.add(new Smartphone(2, "Telefon", 2000.00, 15, smartphoneConfigurations, "Czarny", 2200, smartphoneAccessories));
        products.add(new Electronics(3, "Wireless Mouse", 25.99, 50, null));
        products.add(new Electronics(4, "Bluetooth Keyboard", 45.99, 30, null));
        products.add(new Electronics(5, "External Hard Drive", 89.99, 20, null));
        products.add(new Electronics(6, "Smart TV", 4000.00, 5, null));

    }
}
