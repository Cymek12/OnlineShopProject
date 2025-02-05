package model;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Klasa przechowuje informacje o kliencie oraz koncowa cene zamowienia
 */
public class Order {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private Address deliveryAddress;
    private BigDecimal orderPrice;

    public Order(String firstName, String lastName, String phoneNumber, String emailAddress, Address deliveryAddress, BigDecimal orderPrice) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getRoundedOrderPrice() {
        return orderPrice.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order order)) return false;
        return Objects.equals(firstName, order.firstName) && Objects.equals(lastName, order.lastName) && Objects.equals(phoneNumber, order.phoneNumber) && Objects.equals(emailAddress, order.emailAddress) && Objects.equals(deliveryAddress, order.deliveryAddress) && Objects.equals(orderPrice, order.orderPrice);
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
