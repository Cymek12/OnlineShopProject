package model;

import model.utils.*;

import java.util.Objects;

public class ProductConfiguration <T>{
    private T value;
//    private String type;
//    private String value;
//    private double additionalPrice;

    public ProductConfiguration(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public double getAdditionalPrice(){
        if(value instanceof Processor processor){
            return processor.getAdditionalPrice();
        }
        if(value instanceof RamSize ramSize){
            return ramSize.getAdditionalPrice();
        }
        if(value instanceof GraphicsCard graphicsCard){
            return graphicsCard.getAdditionalPrice();
        }
        if(value instanceof StorageSize storageSize){
            return storageSize.getAdditionalPrice();
        }
        if(value instanceof Color color){
            return color.getAdditionalPrice();
        }
        if(value instanceof BatteryCapacity batteryCapacity){
            return batteryCapacity.getAdditionalPrice();
        }
        if(value instanceof Accessories accessories){
            return accessories.getAdditionalPrice();
        }
        return 0.0;
    }

//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getValue() {
//        return value;
//    }
//
//    public void setValue(String value) {
//        this.value = value;
//    }
//
//    public double getAdditionalPrice() {
//        return additionalPrice;
//    }
//
//    public void setAdditionalPrice(double additionalPrice) {
//        this.additionalPrice = additionalPrice;
//    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProductConfiguration<?> that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

