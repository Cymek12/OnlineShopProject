package model;

import java.util.Objects;

public class ProductConfiguration {
    private String type; // Typ konfiguracji, np. "Kolor", "Procesor"
    private String value; // Wartość konfiguracji, np. "Czarny", "Intel i7"
    private double additionalPrice; // Dodatkowa cena za konfigurację

    public ProductConfiguration(String type, String value, double additionalPrice) {
        this.type = type;
        this.value = value;
        this.additionalPrice = additionalPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return Double.compare(that.additionalPrice, additionalPrice) == 0 &&
                Objects.equals(type, that.type) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value, additionalPrice);
    }

    @Override
    public String toString() {
        return "ProductConfiguration{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", additionalPrice=" + additionalPrice +
                '}';
    }
}
