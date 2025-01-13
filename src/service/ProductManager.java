package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;

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
}
