package service;

import model.*;

import java.math.BigDecimal;
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
                new ProductConfiguration(ConfigurationType.PROCESSOR, "Intel i5", BigDecimal.valueOf(0)),
                new ProductConfiguration(ConfigurationType.PROCESSOR, "Intel i7", BigDecimal.valueOf(800)),
                new ProductConfiguration(ConfigurationType.PROCESSOR, "Intel i9", BigDecimal.valueOf(1200)),
                new ProductConfiguration(ConfigurationType.RAM_SIZE, "8 GB", BigDecimal.valueOf(0)),
                new ProductConfiguration(ConfigurationType.RAM_SIZE, "16 GB", BigDecimal.valueOf(500)),
                new ProductConfiguration(ConfigurationType.RAM_SIZE, "32 GB", BigDecimal.valueOf(1000)),
                new ProductConfiguration(ConfigurationType.GRAPHICS_CARD, "NVIDIA GTX 1080", BigDecimal.valueOf(0)),
                new ProductConfiguration(ConfigurationType.GRAPHICS_CARD, "NVIDIA RTX 3060", BigDecimal.valueOf(1200)),
                new ProductConfiguration(ConfigurationType.GRAPHICS_CARD, "NVIDIA RTX 4070", BigDecimal.valueOf(2700)),
                new ProductConfiguration(ConfigurationType.STORAGE_SIZE, "256 GB", BigDecimal.valueOf(0)),
                new ProductConfiguration(ConfigurationType.STORAGE_SIZE, "512 GB", BigDecimal.valueOf(300)),
                new ProductConfiguration(ConfigurationType.STORAGE_SIZE, "1 TB ", BigDecimal.valueOf(500))
        );

        List<ProductConfiguration> smartphoneConfigurations = List.of(
                new ProductConfiguration(ConfigurationType.COLOR, "Czarny", BigDecimal.valueOf(0)),
                new ProductConfiguration(ConfigurationType.COLOR, "Niebieski", BigDecimal.valueOf(150)),
                new ProductConfiguration(ConfigurationType.COLOR, "Zielony", BigDecimal.valueOf(150)),
                new ProductConfiguration(ConfigurationType.BATTERY_CAPACITY, "2200 mAh", BigDecimal.valueOf(0)),
                new ProductConfiguration(ConfigurationType.BATTERY_CAPACITY, "3200 mAh", BigDecimal.valueOf(400)),
                new ProductConfiguration(ConfigurationType.BATTERY_CAPACITY, "4200 mAh", BigDecimal.valueOf(800)),
                new ProductConfiguration(ConfigurationType.ACCESSORY, "Słuchawki", BigDecimal.valueOf(150)),
                new ProductConfiguration(ConfigurationType.ACCESSORY, "Szybka ładowarka", BigDecimal.valueOf(50)),
                new ProductConfiguration(ConfigurationType.ACCESSORY, "Etui", BigDecimal.valueOf(30)),
                new ProductConfiguration(ConfigurationType.ACCESSORY, "Uchwyt", BigDecimal.valueOf(45))
        );

        List<ProductConfiguration> emptyConfiguration = new ArrayList<>();

        products.add(new Product(1, ProductType.COMPUTER, "Gaming PC", BigDecimal.valueOf(3000.00), 10, computerConfigurations));
        products.add(new Product(2, ProductType.SMARTPHONE, "Telefon", BigDecimal.valueOf(2000.00), 15, smartphoneConfigurations));
        products.add(new Product(3, ProductType.ELECTRONICS, "Wireless Mouse", BigDecimal.valueOf(25.99), 50, emptyConfiguration));
        products.add(new Product(4, ProductType.ELECTRONICS,"Bluetooth Keyboard", BigDecimal.valueOf(45.99), 30, emptyConfiguration));
        products.add(new Product(5, ProductType.ELECTRONICS,"External Hard Drive", BigDecimal.valueOf(89.99), 20, emptyConfiguration));
        products.add(new Product(6, ProductType.ELECTRONICS,"Smart TV", BigDecimal.valueOf(4000.00), 5, emptyConfiguration));

    }
}
