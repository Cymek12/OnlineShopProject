package model.utils;

import exception.WrongIdException;

public enum StorageSize {
    DISK_256(1, "256 GB", 0),
    DISK_512(2, "512 GB", 300),
    DISK_1000(3, "1 TB", 500);

    private int id;
    private String name;
    private double additionalPrice;

    StorageSize(int id, String name, double additionalPrice) {
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

    public static StorageSize findStorageSizeById(int id) throws WrongIdException {
        for (StorageSize value : values()) {
            if(value.id == id){
                return value;
            }
        }
        throw new WrongIdException("Wprowadzono nieistniejący numer ID");
    }

    public static void printStorageSizes(){
        for (StorageSize value : values()) {
            System.out.println(value);
        }
    }

    @Override
    public String toString() {
        return id + ". " + name + " - " + additionalPrice + " zł";
    }
}
