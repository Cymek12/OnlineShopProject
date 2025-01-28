package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product {
    private int id;
    private String type;
    private String name;
    private double basePrice;
    private int availableQuantity;
    private List<ProductConfiguration> availableConfigurations;
    private List<ProductConfiguration> chosenConfiguration = new ArrayList<>();
    private List<ProductConfiguration> accessories = new ArrayList<>();

    public Product(int id, String type, String name, double basePrice, int availableQuantity, List<ProductConfiguration> availableConfigurations) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public List<ProductConfiguration> getChosenConfiguration() {
        return chosenConfiguration;
    }

    public void setChosenConfiguration(List<ProductConfiguration> chosenConfiguration) {
        this.chosenConfiguration = chosenConfiguration;
    }

    public List<ProductConfiguration> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<ProductConfiguration> accessories) {
        this.accessories = accessories;
    }

    public double calculateTotalPrice() {
        return 1;//chosenConfiguration.stream().mapToDouble(ProductConfiguration::getAdditionalPrice).sum() + basePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return id == product.id && Double.compare(basePrice, product.basePrice) == 0 && availableQuantity == product.availableQuantity && Objects.equals(type, product.type) && Objects.equals(name, product.name) && Objects.equals(availableConfigurations, product.availableConfigurations) && Objects.equals(chosenConfiguration, product.chosenConfiguration) && Objects.equals(accessories, product.accessories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, basePrice, availableQuantity, availableConfigurations, chosenConfiguration, accessories);
    }

    @Override
    public String toString() {
        return "Id: " + id + ", nazwa: " + name + ", cena podstawowa:" + basePrice + "zł, dostępność: " + availableQuantity;
    }
}
