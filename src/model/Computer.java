package model;

import java.util.Objects;

public class Computer extends Product{
    private String processor;
    private int ramSize;
    private String graphicsCard;
    private int storageSize;

    public Computer(int id, String name, double price, int availableQuantity, String processor, int ramSize, String graphicsCard, int storageSize) {
        super(id, name, price, availableQuantity);
        this.processor = processor;
        this.ramSize = ramSize;
        this.graphicsCard = graphicsCard;
        this.storageSize = storageSize;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
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
        if (!super.equals(o)) return false;
        return ramSize == computer.ramSize && storageSize == computer.storageSize && Objects.equals(processor, computer.processor) && Objects.equals(graphicsCard, computer.graphicsCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), processor, ramSize, graphicsCard, storageSize);
    }

    @Override
    public String toString() {
        return super.toString() +
                "Computer{" +
                "processor='" + processor + '\'' +
                ", ramSize=" + ramSize +
                ", graphicsCard='" + graphicsCard + '\'' +
                ", storageSize=" + storageSize +
                '}';
    }
}
