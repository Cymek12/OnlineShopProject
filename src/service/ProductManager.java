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
        List<ProductConfiguration> computerConfigurations = List.of(
                new ProductConfiguration(ConfigurationType.PROCESSOR, "Intel i5", 0),
                new ProductConfiguration(ConfigurationType.PROCESSOR, "Intel i7", 800),
                new ProductConfiguration(ConfigurationType.PROCESSOR, "Intel i9", 1200),
                new ProductConfiguration(ConfigurationType.RAM_SIZE, "8", 0),
                new ProductConfiguration(ConfigurationType.RAM_SIZE, "16", 500),
                new ProductConfiguration(ConfigurationType.RAM_SIZE, "32", 1000),
                new ProductConfiguration(ConfigurationType.GRAPHICS_CARD, "NVIDIA GTX 1080", 0),
                new ProductConfiguration(ConfigurationType.GRAPHICS_CARD, "NVIDIA RTX 3060", 1200),
                new ProductConfiguration(ConfigurationType.GRAPHICS_CARD, "NVIDIA RTX 4070", 2700),
                new ProductConfiguration(ConfigurationType.STORAGE_SIZE, "256", 0),
                new ProductConfiguration(ConfigurationType.STORAGE_SIZE, "512", 300),
                new ProductConfiguration(ConfigurationType.STORAGE_SIZE, "1000", 500)
        );

        List<ProductConfiguration> smartphoneConfigurations = List.of(
                new ProductConfiguration(ConfigurationType.COLOR, "Czarny", 0),
                new ProductConfiguration(ConfigurationType.COLOR, "Niebieski", 150),
                new ProductConfiguration(ConfigurationType.COLOR, "Zielony", 150),
                new ProductConfiguration(ConfigurationType.BATTERY_CAPACITY, "2200", 0),
                new ProductConfiguration(ConfigurationType.BATTERY_CAPACITY, "3200", 400),
                new ProductConfiguration(ConfigurationType.BATTERY_CAPACITY, "4200", 800),
                new ProductConfiguration(ConfigurationType.ACCESSORY, "Słuchawki", 150),
                new ProductConfiguration(ConfigurationType.ACCESSORY, "Szybka ładowarka", 50),
                new ProductConfiguration(ConfigurationType.ACCESSORY, "Etui", 30),
                new ProductConfiguration(ConfigurationType.ACCESSORY, "Uchwyt", 45)
        );

        List<ProductConfiguration> emptyConfiguration = new ArrayList<>();

        products.add(new Product(1, ProductType.COMPUTER, "Gaming PC", 3000.00, 10, computerConfigurations));
        products.add(new Product(2, ProductType.SMARTPHONE, "Telefon", 2000.00, 15, smartphoneConfigurations));
        products.add(new Product(3, ProductType.ELECTRONICS, "Wireless Mouse", 25.99, 50, emptyConfiguration));
        products.add(new Product(4, ProductType.ELECTRONICS,"Bluetooth Keyboard", 45.99, 30, emptyConfiguration));
        products.add(new Product(5, ProductType.ELECTRONICS,"External Hard Drive", 89.99, 20, emptyConfiguration));
        products.add(new Product(6, ProductType.ELECTRONICS,"Smart TV", 4000.00, 5, emptyConfiguration));

    }
}
