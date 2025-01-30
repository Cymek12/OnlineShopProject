package model;


import java.util.Objects;

/**
 * Klasa przechowuje informacje o kliencie oraz koncowa cene zamowienia
 */
public class Order {
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String emailAddress;
    private String deliveryAddress;
    private double orderPrice;

    public Order(String firstName, String lastName, int phoneNumber, String emailAddress, String deliveryAddress, double orderPrice) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.deliveryAddress = deliveryAddress;
        this.orderPrice = orderPrice;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public double getRoundedOrderPrice() {
        return Math.round(orderPrice * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order order)) return false;
        return phoneNumber == order.phoneNumber && Double.compare(orderPrice, order.orderPrice) == 0 && Objects.equals(firstName, order.firstName) && Objects.equals(lastName, order.lastName) && Objects.equals(emailAddress, order.emailAddress) && Objects.equals(deliveryAddress, order.deliveryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber, emailAddress, deliveryAddress, orderPrice);
    }

    @Override
    public String toString() {
        return "Order{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", emailAddress='" + emailAddress + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
