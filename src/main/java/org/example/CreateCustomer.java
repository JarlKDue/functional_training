package org.example;

import org.example.functions.OutputGiver;

public class CreateCustomer implements OutputGiver<Customer> {
    @Override
    public Customer giveOutput(String input) {
        Customer customer = new Customer("Hello");
        customer.setName(input);
        return customer;
    }
}

class Customer{
    String name;

    Customer(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}