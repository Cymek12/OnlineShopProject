package service;

import exception.EmptyCartException;
import exception.NotAvailableInStorageException;
import model.Computer;
import model.Product;
import model.ProductConfiguration;
import model.Smartphone;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Klasa przechowuje liste dodanych do koszyka produktów. Zawiera metody ktore pozwalaja modyfikowac liste. Dodatkowo przechowuje metody modyfikujace dodane do koszyka produkty.
 */
public class Cart {
    private List<Product> addedProducts = new ArrayList<>();

    public void addProductToCart(Product product) throws NotAvailableInStorageException {
        if(product.getAvailableQuantity() == 0){
            throw new NotAvailableInStorageException("Brak produktu w magazynie");
        }

        addedProducts.add(product);
        product.setAvailableQuantity(product.getAvailableQuantity() - 1);
        System.out.println("Dodano do koszyka: " + DisplayFormatter.getProductToCart(product));

    }

    public List<Product> getAddedProducts() throws EmptyCartException {
        if(addedProducts.isEmpty()){
            throw new EmptyCartException("Niepowodzenie operacji - Koszyk jest pusty!");
        }
        return addedProducts;
    }

    public boolean isCartEmpty(){
        return addedProducts.isEmpty();
    }

    public void printAddedProducts() throws EmptyCartException {
        for (Product addedProduct : getAddedProducts()) {
            System.out.println(DisplayFormatter.getProductToCart(addedProduct));
        }
    }

    public double getOrderPrice() {
        double basePriceSum = addedProducts.stream().mapToDouble(Product::getBasePrice).sum();

        double additionalSum = addedProducts.stream()
                .map(s -> s.getChosenConfiguration().stream()
                        .mapToDouble(ProductConfiguration::getAdditionalPrice))
                .flatMapToDouble(ds -> ds)
                .sum();

        return  basePriceSum + additionalSum;
    }

    public void clearAddedProducts(){
        addedProducts.clear();
    }

    public Optional<Product> findAddedProductById(int id){
        return addedProducts.stream().filter(product -> product.getId() == id).findFirst();
    }

    public void deleteProductFromCart(int id){
        Optional<Product> optAddedProduct = findAddedProductById(id);
        if(optAddedProduct.isPresent()){
            addedProducts.remove(optAddedProduct.get());
            optAddedProduct.get().setAvailableQuantity(optAddedProduct.get().getAvailableQuantity() + 1);
            System.out.println("Usunięto produkt z koszyka");
        }
        else {
            System.out.println("Produkt o podanym Id nie znajduje się w koszyku");
        }
    }

    public void configureComputer(Product product, List<ProductConfiguration> chosenConfiguration){
        for (Product addedProduct : addedProducts) {
            if(addedProduct.getId() == product.getId()){
                addedProduct.setChosenConfiguration(chosenConfiguration);
            }
        }
        System.out.println("Zmieniono konfiguracje komputera!");
        System.out.println(product);
        chosenConfiguration.forEach(System.out::println);
    }

    public void configureSmartphone(Product product, List<ProductConfiguration> chosenConfiguration, List<ProductConfiguration> chosenAccessories){
        for (Product addedProduct : addedProducts) {
            if(addedProduct.getId() == product.getId()){
                addedProduct.setChosenConfiguration(chosenConfiguration);
                addedProduct.setAccessories(chosenAccessories);
            }
        }
        System.out.println("Zmieniono konfiguracje telefonu!");
        System.out.println(product);
        chosenConfiguration.forEach(System.out::println);
        chosenAccessories.forEach(System.out::println);
    }
}
