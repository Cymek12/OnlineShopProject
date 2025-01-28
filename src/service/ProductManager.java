package service;

import model.*;
import model.utils.*;

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
        //typ enum
        List<ProductConfiguration> computerConfigurations = List.of(
                new ProductConfiguration<>(Processor.class),
                new ProductConfiguration<>(RamSize.class),
                new ProductConfiguration<>(GraphicsCard.class),
                new ProductConfiguration<>(StorageSize.class)
        );

        List<ProductConfiguration> smartphoneConfigurations = List.of(
                new ProductConfiguration<>(Color.class),
                new ProductConfiguration<>(BatteryCapacity.class)
        );

        List<ProductConfiguration> smartphoneAccessories = List.of(
                new ProductConfiguration<>(Accessories.class)
        );

        List<ProductConfiguration> emptyConfiguration = new ArrayList<>();

        products.add(new Product(1, "Computer", "Gaming PC", 3000.00, 10, computerConfigurations));
        products.add(new Product(2, "Smartphone", "Telefon", 2000.00, 15, smartphoneConfigurations));
        products.add(new Product(3, "Electronics", "Wireless Mouse", 25.99, 50, emptyConfiguration));
        products.add(new Product(4, "Electronics","Bluetooth Keyboard", 45.99, 30, emptyConfiguration));
        products.add(new Product(5, "Electronics","External Hard Drive", 89.99, 20, emptyConfiguration));
        products.add(new Product(6, "Electronics","Smart TV", 4000.00, 5, emptyConfiguration));

    }
}
