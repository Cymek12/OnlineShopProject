package model;

import java.util.List;
import java.util.Objects;

public class Smartphone{
    private Product product;
    private String color;
    private int batteryCapacity;
    private List<ProductConfiguration> accessories;

    public Smartphone(Product product, String color, int batteryCapacity, List<ProductConfiguration> accessories) {
        this.product = product;
        this.color = color;
        this.batteryCapacity = batteryCapacity;
        this.accessories = accessories;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public List<ProductConfiguration> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<ProductConfiguration> accessories) {
        this.accessories = accessories;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Smartphone that)) return false;
        return batteryCapacity == that.batteryCapacity && Objects.equals(product, that.product) && Objects.equals(color, that.color) && Objects.equals(accessories, that.accessories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, color, batteryCapacity, accessories);
    }

    @Override
    public String toString() {
        return product + ", kolor: " + color + ", pojemność baterii: " + batteryCapacity + ", akcesoria: " + accessories;
    }
}
