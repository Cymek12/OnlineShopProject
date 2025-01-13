package service;

import model.Computer;
import model.Electronics;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductManager {
    List<Product> products;



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
//        products.add(new Computer(101, "Gaming PC", 2499.99, 5, "Intel Core i7", 16, "NVIDIA RTX 3060", 1000));
//        products.add(new Computer(102, "Office Laptop" , 999.99, 10,))
    }
}
