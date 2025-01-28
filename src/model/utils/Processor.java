package model.utils;

import exception.WrongIdException;

public enum Processor {
    INTEL_I5(1, "Intel i5",0),
    INTEL_I7(2, "Intel i7",800),
    INTEL_I9(3, "Intel i9",1200);

    private int id;
    private String name;
    private double additionalPrice;

    Processor(int id, String name, double additionalPrice) {
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

    public static Processor findProcessorById(int id) throws WrongIdException {
        for (Processor value : values()) {
            if(value.id == id){
                return value;
            }
        }
        throw new WrongIdException("Wprowadzono nieistniejący numer ID");
    }

    public static void printProcessors(){
        for (Processor value : values()) {
            System.out.println(value);
        }
    }

    @Override
    public String toString() {
        return id + ". " + name + " - " + additionalPrice + " zł";
    }
}
