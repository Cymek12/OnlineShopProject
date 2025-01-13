package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> addedProducts;

    public Cart() {
        this.addedProducts = new ArrayList<>();
    }

    public List<Product> getAddedProducts() {
        return addedProducts;
    }

    public void setAddedProducts(List<Product> addedProducts) {
        this.addedProducts = addedProducts;
    }


}
