package model.utils;

import exception.WrongIdException;

public enum BatteryCapacity {
    MAH_2200(1, "2200 mAh", 0),
    MAH_3200(2, "3200 mAh", 400),
    MAH_4200(3, "4200 mAh", 800);

    private int id;
    private String name;
    private double additionalPrice;

    BatteryCapacity(int id, String name, double additionalPrice) {
        this.id = id;
        this.name = name;
        this.additionalPrice = additionalPrice;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAdditionalPrice() {
        return additionalPrice;
    }

    public static BatteryCapacity findBatteryCapacityById(int id) throws WrongIdException {
        for (BatteryCapacity value : values()) {
            if(value.id == id){
                return value;
            }
        }
        throw new WrongIdException("Wprowadzono nieistniejący numer ID");
    }

    public static void printBatteryCapacities(){
        for (BatteryCapacity value : values()) {
            System.out.println(value);
        }
    }

    @Override
    public String toString() {
        return id + ". " + name + " - " + additionalPrice + " zł";
    }
}
