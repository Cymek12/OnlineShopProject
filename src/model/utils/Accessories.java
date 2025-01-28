package model.utils;

import exception.WrongIdException;

public enum Accessories {
    HEADPHONES(1, "Słuchawki", 150),
    FAST_CHARGER(2, "Szybka ładowarka", 50),
    CASE(3, "Etui", 30),
    HOLDER(4, "Uchwyt", 45);

    private int id;
    private String name;
    private double additionalPrice;

    Accessories(int id, String name, double additionalPrice) {
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

    public static Accessories findAccessoriesById(int id) throws WrongIdException {
        for (Accessories value : values()) {
            if(value.id == id){
                return value;
            }
        }
        throw new WrongIdException("Wprowadzono nieistniejący numer ID");
    }

    public static void printAccessories(){
        for (Accessories value : values()) {
            System.out.println(value);
        }
    }

    @Override
    public String toString() {
        return id + ". " + name + " - " + additionalPrice + " zł";
    }
}
