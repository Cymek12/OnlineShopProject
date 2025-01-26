package model;

import java.util.List;
import java.util.Objects;

public class Smartphone extends Product{
    private String color;
    private int batteryCapacity;
    private String accessories;

    public Smartphone(int id, String name, double price, int availableQuantity, List<ProductConfiguration> configurations, String color, int batteryCapacity, String accessories) {
        super(id, name, price, availableQuantity, configurations);
        this.color = color;
        this.batteryCapacity = batteryCapacity;
        this.accessories = accessories;
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

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Smartphone that)) return false;
        if (!super.equals(o)) return false;
        return batteryCapacity == that.batteryCapacity && Objects.equals(color, that.color) && Objects.equals(accessories, that.accessories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color, batteryCapacity, accessories);
    }

    @Override
    public String toString() {
        return super.toString() + ", kolor: " + color + ", pojemność baterii: " + batteryCapacity + ", akcesoria: " + accessories;
    }
}
