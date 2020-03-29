package org.example.dataobjects;

public class Customer_Address_Old {
    String address;
    String format;
    String name;

    public Customer_Address_Old(String address, String format, String name){
        this.address = address;
        this.format = format;
        this.name = name;
    }

    public Customer_Address_Old(){

    }

    public String getName() {
        return name;
    }

    public String getFormat() {
        return format;
    }

    public String getAddress() {
        return address;
    }
}
