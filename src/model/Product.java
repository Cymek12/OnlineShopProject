package model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Product {
    private Integer id;
    private ProductType type;
    private String name;
    private BigDecimal basePrice;
    private int availableQuantity;
    private List<ProductConfiguration> availableConfigurations;

    public Product(Integer id, ProductType type, String name, BigDecimal basePrice, int availableQuantity, List<ProductConfiguration> availableConfigurations) {
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

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
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

    public Map<ConfigurationType, List<ProductConfiguration>> getGroupedConfiguration() {
        Map<ConfigurationType, List<ProductConfiguration>> groupedConfiguration = new HashMap<>();
        for (ProductConfiguration availableConfiguration : availableConfigurations) {
            groupedConfiguration.put(availableConfiguration.getType(), availableConfigurations);
        }
        return groupedConfiguration;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return availableQuantity == product.availableQuantity && Objects.equals(id, product.id) && type == product.type && Objects.equals(name, product.name) && Objects.equals(basePrice, product.basePrice) && Objects.equals(availableConfigurations, product.availableConfigurations);
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
