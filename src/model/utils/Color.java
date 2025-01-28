package model.utils;

import exception.WrongIdException;

public enum Color {
    BLACK(1, "Czarny", 0),
    BLUE(2, "Niebieski", 150),
    GREEN(3, "Zielony", 150);

    private int id;
    private String name;
    private double additionalPrice;

    Color(int id, String name, double additionalPrice) {
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

    public static Color findColorById(int id) throws WrongIdException {
        for (Color value : values()) {
            if(value.id == id){
                return value;
            }
        }
        throw new WrongIdException("Wprowadzono nieistniejący numer ID");
    }

    public static void printColors(){
        for (Color value : values()) {
            System.out.println(value);
        }
    }

    @Override
    public String toString() {
        return id + ". " + name + " - " + additionalPrice + " zł";
    }
}
