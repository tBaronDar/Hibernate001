package org.example;

import jakarta.persistence.Embeddable;

@Embeddable //this will spread the Address in the alien table
public class Address {
    private String city;
    private String street;
    private int streetNumber;

    public Address(){}//required by hibernate

    public Address(String city, String street, int streetNumber) {
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber=" + streetNumber +
                '}';
    }
}
