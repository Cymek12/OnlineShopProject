package model.utils;

import exception.WrongIdException;

public enum RamSize {
    RAM_8(1, "8 GB", 0),
    RAM_16(2, "16 GB", 500),
    RAM_32(3, "32 GB", 1000);

    private int id;
    private String name;
    private double additionalPrice;

    RamSize(int id, String name, double additionalPrice) {
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

    public static RamSize findRamSizeById(int id) throws WrongIdException {
        for (RamSize value : values()) {
            if(value.id == id){
                return value;
            }
        }
        throw new WrongIdException("Wprowadzono nieistniejący numer ID");
    }

    public static void printRamSizes(){
        for (RamSize value : values()) {
            System.out.println(value);
        }
    }

    @Override
    public String toString() {
        return id + ". " + name + " - " + additionalPrice + " zł";
    }
}
