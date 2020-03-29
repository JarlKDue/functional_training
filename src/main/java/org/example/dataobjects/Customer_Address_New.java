package org.example.dataobjects;

public class Customer_Address_New {
    String street;
    String country;
    String number;
    String city;

    public Customer_Address_New(String street, String city, String country, String number){
        this.street = street;
        this.city = city;
        this.country = country;
        this.number = number;
    }

    public Customer_Address_New(){

    }
}
