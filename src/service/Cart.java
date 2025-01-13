package service;

import exception.NotAvailableInStorageException;
import model.Product;

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

}
