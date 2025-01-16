package service;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa zawiera logikę implementacji kodów ziżkowych
 */
public class DiscountManager {
    private Map<String, Double> discountCodes;

    public DiscountManager() {
        this.discountCodes = new HashMap<>();
        this.discountCodes.put("ZIMA10", 0.10);
        this.discountCodes.put("LATO15", 0.15);
        this.discountCodes.put("WIOSNA20", 0.20);
    }

    private Double getDiscount(String code){
        return discountCodes.getOrDefault(code, null);
    }

    public double applyDiscount(String code){
        Double discount = getDiscount(code);

        if(code.isEmpty()){
            System.out.println("Nie użyto kodu zniżkowego");
            return 1;
        }
        else if(discount == null){
            System.out.println("Nieprawidłowy kod zniżkowy");
            return 1;
        }
        else {
            System.out.println("Zastosowano zniżkę " + (discount * 100) + "% na całe zamówienie");
            return 1 - discount;
        }
    }
}
