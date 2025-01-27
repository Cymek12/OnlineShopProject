package model;

import java.util.List;

public class Electronics extends Product{
    public Electronics(int id, String name, double basePrice, int availableQuantity, List<ProductConfiguration> configurations) {
        super(id, name, basePrice, availableQuantity, configurations);
    }
}
