package com.project.UserService.domain;

public class Address {
    private int  HouseNo;
    private String city;
    private String street;
    private String postalCode;



    public Address() {
    }

    public Address(int houseNo, String city, String street, String postalCode) {
        HouseNo = houseNo;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }


    public int getHouseNo() {
        return HouseNo;
    }

    public void setHouseNo(int houseNo) {
        HouseNo = houseNo;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    @Override
    public String toString() {
        return "Address{" +
                "HouseNo=" + HouseNo +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}

