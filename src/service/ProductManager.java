package service;

import model.Computer;
import model.Electronics;
import model.Product;
import model.Smartphone;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Klasa zawiera liste dostepnych produktow w sklepi oraz metody umozliwiajace podstawowe operacje na liscie. Dodatkowo zaiwera metode implementujaca dane testowe do sklepu.
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

    public void exampleData(){
        products.add(new Electronics(1, "Wireless Mouse", 25.99, 50));
        products.add(new Electronics(2, "Bluetooth Keyboard", 45.99, 30));
        products.add(new Electronics(3, "External Hard Drive", 89.99, 20));
        products.add(new Computer(101, "Gaming PC", 2499.99, 5, "Intel Core i7", 16, "NVIDIA RTX 3060", 1000));
        products.add(new Computer(102, "Office Laptop", 999.99, 10, "AMD Ryzen 5", 8, "Integrated Graphics", 512));
        products.add(new Computer(103, "Ultrabook", 1499.99, 8, "Intel Core i5", 16, "Integrated Graphics", 256));
        products.add(new Smartphone(201, "Galaxy S22", 799.99, 20, "Black", 4000, "Charger, Earphones"));
        products.add(new Smartphone(202, "iPhone 14", 1099.99, 15, "Silver", 3279, "Charger, Lightning Cable"));
        products.add(new Smartphone(203, "Pixel 7", 599.99, 30, "White", 4350, "Charger, Protective Case"));
    }
}
