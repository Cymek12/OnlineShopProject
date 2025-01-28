package model;

import java.util.List;
import java.util.Objects;


public class Product {
    private int id;
    private ProductType type;
    private String name;
    private double basePrice;
    private int availableQuantity;
    private List<ProductConfiguration> availableConfigurations;

    public Product(int id, ProductType type, String name, double basePrice, int availableQuantity, List<ProductConfiguration> availableConfigurations) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.basePrice = basePrice;
        this.availableQuantity = availableQuantity;
        this.availableConfigurations = availableConfigurations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public List<ProductConfiguration> getAvailableConfigurations() {
        return availableConfigurations;
    }

    public void setAvailableConfigurations(List<ProductConfiguration> availableConfigurations) {
        this.availableConfigurations = availableConfigurations;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return id == product.id && Double.compare(basePrice, product.basePrice) == 0 && availableQuantity == product.availableQuantity && type == product.type && Objects.equals(name, product.name) && Objects.equals(availableConfigurations, product.availableConfigurations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, basePrice, availableQuantity, availableConfigurations);
    }

    @Override
    public String toString() {
        return "Id: " + id + ", nazwa: " + name + ", cena podstawowa: " + basePrice + "zł, dostępność: " + availableQuantity;
    }
}
