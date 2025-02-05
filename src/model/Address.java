package model;

import java.util.Objects;

public class Address {
    private String province;
    private String city;
    private String zipCode;
    private String street;

    public Address(String province, String city, String zipCode, String street) {
        this.province = province;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Address address)) return false;
        return Objects.equals(province, address.province) && Objects.equals(city, address.city) && Objects.equals(zipCode, address.zipCode) && Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(province, city, zipCode, street);
    }

    @Override
    public String toString() {
        return province + "\n" +
                city + "\n" +
                zipCode + "\n" +
                street;
    }
}
