package service;

import exception.EmptyCartException;
import exception.NotAvailableInStorageException;
import model.Product;
import model.ProductConfiguration;
import model.ProductType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Klasa przechowuje liste dodanych do koszyka produktów. Zawiera metody ktore pozwalaja modyfikowac liste.
 */
public class Cart {
    private List<CartItem> addedProducts = new ArrayList<>();

    public void addProductToCart(CartItem cartItem) throws NotAvailableInStorageException {
        if(cartItem.getProduct().getAvailableQuantity() == 0){
            throw new NotAvailableInStorageException("Brak produktu w magazynie");
        }

        addedProducts.add(cartItem);
        synchronized (Cart.class){
            cartItem.getProduct().setAvailableQuantity(cartItem.getProduct().getAvailableQuantity() - 1);
        }
        System.out.println("Dodano do koszyka: " + ProductFormatter.getProductToCart(cartItem));

    }

    public List<CartItem> getAddedProducts() throws EmptyCartException {
        if(addedProducts.isEmpty()){
            throw new EmptyCartException("Niepowodzenie operacji - Koszyk jest pusty!");
        }
        return addedProducts;
    }

    public boolean isCartEmpty(){
        return addedProducts.isEmpty();
    }

    public void printAddedProducts() throws EmptyCartException {
        for (CartItem addedProduct : getAddedProducts()) {
            System.out.println(ProductFormatter.getProductToCart(addedProduct));
        }
    }

    public double getOrderPrice() {
        double basePriceSum = addedProducts.stream().mapToDouble(s -> s.getProduct().getBasePrice()).sum();

        double additionalSum = addedProducts.stream()
                .map(s -> s.getChosenConfigurations().stream()
                        .mapToDouble(ProductConfiguration::getAdditionalPrice))
                .flatMapToDouble(ds -> ds)
                .sum();

        return  basePriceSum + additionalSum;
    }

    /**
     * Usuwa wszystkie produkty z koszyka i przywraca dostępność w magazynie
     */
    public void clearAddedProducts(){
        for (CartItem addedProduct : addedProducts) {
            Product product = addedProduct.getProduct();
            synchronized (Cart.class){
                product.setAvailableQuantity(product.getAvailableQuantity() + 1);
            }
        }
        addedProducts.clear();
    }

    public Optional<CartItem> findAddedProductById(int id){
        return addedProducts.stream().filter(p -> p.getProduct().getId() == id).findFirst();
    }

    /**
     * Usuwa wybrany produkt z koszyka i przywraca dostępność w magazynie
     */
    public void deleteProductFromCart(int id){
        Optional<CartItem> optAddedProduct = findAddedProductById(id);
        if(optAddedProduct.isEmpty()){
            System.out.println("Produkt o podanym Id nie znajduje się w koszyku");
            return;
        }
        addedProducts.remove(optAddedProduct.get());
        Product product = optAddedProduct.get().getProduct();
        synchronized (Cart.class){
            product.setAvailableQuantity(product.getAvailableQuantity() + 1);
        }
        System.out.println("Usunięto produkt z koszyka");
    }
}
