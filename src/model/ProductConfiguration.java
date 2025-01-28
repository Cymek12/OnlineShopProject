package model;

import java.util.Objects;

public class ProductConfiguration{
    private ConfigurationType configurationType;
    private String value;
    private double additionalPrice;

    public ProductConfiguration(ConfigurationType configurationType, String value, double additionalPrice) {
        this.configurationType = configurationType;
        this.value = value;
        this.additionalPrice = additionalPrice;
    }

    public ConfigurationType getType() {
        return configurationType;
    }

    public void setType(ConfigurationType configurationType) {
        this.configurationType = configurationType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getAdditionalPrice() {
        return additionalPrice;
    }

    public void setAdditionalPrice(double additionalPrice) {
        this.additionalPrice = additionalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProductConfiguration that)) return false;
        return Double.compare(additionalPrice, that.additionalPrice) == 0 && configurationType == that.configurationType && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(configurationType, value, additionalPrice);
    }

    @Override
    public String toString() {
        return value + " - " + additionalPrice + " zł";
    }
}

