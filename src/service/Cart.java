package service;

import exception.EmptyCartException;
import exception.NotAvailableInStorageException;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> addedProducts = new ArrayList<>();

    public void addProductToCart(Product product){
        if(product == null){
            throw new NullPointerException("Product cannot be null ");
        }
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

    public List<Product> getAddedProducts(){
        if(addedProducts.isEmpty()){
            throw new EmptyCartException("Cart is empty");
        }
        return addedProducts;
    }

    public double getOrderPrice() {
        return addedProducts.stream().mapToDouble(Product::getPrice).sum();
    }

}
