package org.example;

import org.example.functions.OutputGiver;
import org.junit.Test;

import java.util.function.Function;

public class TestOutputGiver {

    @Test
    public void testOutPutGiver(){
        OutputGiver<Customer> createCustomer = new CreateCustomer();
        Function<Customer, newCustomer> f = customer -> new newCustomer(customer.getName(), "New", "1234");
        OutputGiver<newCustomer> newCustomer = createCustomer.map(f);
        Function<newCustomer, Customer> fc = newCustomer1 -> new Customer(newCustomer1.getName());
        OutputGiver<Customer> customerOutputGiver = newCustomer.map(fc);
        newCustomer newCustomer1 = newCustomer.giveOutput("ACME");
    }

}


class newCustomer{
    String name;
    String type;
    String route;
    newCustomer(String name, String type, String route){
        this.name = name;
        this.type = type;
        this.route = route;
    }

    public String getName() {
        return name;
    }
}