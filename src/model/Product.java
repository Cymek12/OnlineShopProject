package model;

import java.util.List;
import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private double basePrice;
    private int availableQuantity;
    private List<ProductConfiguration> configurations;

    public Product(int id, String name, double basePrice, int availableQuantity, List<ProductConfiguration> configurations) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.availableQuantity = availableQuantity;
        this.configurations = configurations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<ProductConfiguration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<ProductConfiguration> configurations) {
        this.configurations = configurations;
    }

    public void addConfiguration(ProductConfiguration configuration) {
        this.configurations.add(configuration);
    }

    public double calculateTotalPrice() {
        return configurations.stream().mapToDouble(ProductConfiguration::getAdditionalPrice).sum() + basePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(basePrice, product.basePrice) == 0 && availableQuantity == product.availableQuantity && Objects.equals(name, product.name) && Objects.equals(configurations, product.configurations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, basePrice, availableQuantity, configurations);
    }

    @Override
    public String toString() {
        return "Id: " + id + ", nazwa: " + name + ", cena podstawowa:" + basePrice + "zł, dostępność: " + availableQuantity;
    }
}
