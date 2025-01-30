package service;

import model.Product;
import model.ProductConfiguration;

import java.util.List;
import java.util.Objects;

/**
 * klasa reprezentuje produkt dodany do koszyka wraz z jego konfiguracją
 */

public class CartItem {
    private Product product;
    private List<ProductConfiguration> chosenConfigurations;

    public CartItem(Product product, List<ProductConfiguration> chosenConfigurations) {
        this.product = product;
        this.chosenConfigurations = chosenConfigurations;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<ProductConfiguration> getChosenConfigurations() {
        return chosenConfigurations;
    }

    public void setChosenConfigurations(List<ProductConfiguration> chosenConfigurations) {
        this.chosenConfigurations = chosenConfigurations;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CartItem cartItem)) return false;
        return Objects.equals(product, cartItem.product) && Objects.equals(chosenConfigurations, cartItem.chosenConfigurations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, chosenConfigurations);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", chosenConfigurations=" + chosenConfigurations +
                '}';
    }
}
