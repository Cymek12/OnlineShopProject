package model;


import java.util.Objects;

public class Electronics{
    private Product product;

    public Electronics(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Electronics that)) return false;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(product);
    }

    @Override
    public String toString() {
        return product.toString();
    }
}
