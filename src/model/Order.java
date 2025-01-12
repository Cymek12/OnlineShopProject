package model;


import service.Cart;

import java.util.Objects;

public class Order {
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String emailAddress;
    private String deliveryAddress;
    private Cart cart;

    public Order(String firstName, String lastName, int phoneNumber, String emailAddress, String deliveryAddress, Cart cart) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.deliveryAddress = deliveryAddress;
        this.cart = cart;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order order)) return false;
        return phoneNumber == order.phoneNumber && Objects.equals(firstName, order.firstName) && Objects.equals(lastName, order.lastName) && Objects.equals(emailAddress, order.emailAddress) && Objects.equals(deliveryAddress, order.deliveryAddress) && Objects.equals(cart, order.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber, emailAddress, deliveryAddress, cart);
    }

    @Override
    public String toString() {
        return "Order{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", emailAddress='" + emailAddress + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", cart=" + cart +
                '}';
    }
}
