package model;

import model.utils.Processor;

import java.util.Objects;

public class Computer{
    private Product product;
    private Processor processor;
    private int ramSize;
    private String graphicsCard;
    private int storageSize;

    public Computer(Product product, Processor processor, int ramSize, String graphicsCard, int storageSize) {
        this.product = product;
        this.processor = processor;
        this.ramSize = ramSize;
        this.graphicsCard = graphicsCard;
        this.storageSize = storageSize;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public int getRamSize() {
        return ramSize;
    }

    public void setRamSize(int ramSize) {
        this.ramSize = ramSize;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(int storageSize) {
        this.storageSize = storageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Computer computer)) return false;
        return ramSize == computer.ramSize && storageSize == computer.storageSize && Objects.equals(product, computer.product) && processor == computer.processor && Objects.equals(graphicsCard, computer.graphicsCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, processor, ramSize, graphicsCard, storageSize);
    }

    @Override
    public String toString() {
        return product + ", procesor: " + processor + ", ilość RAM: " + ramSize + ", karta graficzna: " + graphicsCard + ", wielkość dysku: " + storageSize;
    }
}
