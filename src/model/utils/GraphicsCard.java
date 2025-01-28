package model.utils;

import exception.WrongIdException;

public enum GraphicsCard {
    GTX_1080(1, "NVIDIA GTX 1080", 0),
    RTX_3060(2, "NVIDIA RTX 3060", 1200),
    RTX_4070(3, "NVIDIA RTX 4070", 2700);

    private int id;
    private String name;
    private double additionalPrice;

    GraphicsCard(int id, String name, double additionalPrice) {
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

    public static GraphicsCard findGraphicsCardById(int id) throws WrongIdException {
        for (GraphicsCard value : values()) {
            if(value.id == id){
                return value;
            }
        }
        throw new WrongIdException("Wprowadzono nieistniejący numer ID");
    }

    public static void printGraphicsCards(){
        for (GraphicsCard value : values()) {
            System.out.println(value);
        }
    }

    @Override
    public String toString() {
        return id + ". " + name + " - " + additionalPrice + " zł";
    }
}
