package org.example;

import org.example.dataobjects.BankAccount_Old;
import org.example.dataobjects.Customer_Address_Old;
import org.example.dataobjects.Customer_New;
import org.example.dataobjects.Customer_Old;
import org.example.functions.Convert_Old_Customer_To_New;
import org.example.functions.List_Of_X_To_List_Of_Y;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestCustomerConverter {

    @Test
    public void when_given_old_customer_convert_to_new(){
        Customer_Old customer_old = new Customer_Old();
        customer_old.setDebit("4200");
        customer_old.setIndebt(false);
        customer_old.setBankAccount_old(new BankAccount_Old("1234-Duckburg", 49201));
        customer_old.setCustomer_address_old(new Customer_Address_Old("CapitolHill:Washington:USA", "42992", "ACME"));
        Customer_New customer_new = Convert_Old_Customer_To_New.convert_old_customer_to_new_customer.apply(customer_old);
    }

    @Test
    public void when_given_list_of_old_customers_convert_to_new(){
        List<Customer_Old> customer_olds = generateOldCustomersList();
        Optional<List<Customer_New>> customer_newList = List_Of_X_To_List_Of_Y.convert_x_to_y(Optional.of(customer_olds), Convert_Old_Customer_To_New.convert_old_customer_to_new_customer);
    }

    private List<Customer_Old> generateOldCustomersList() {
        List<Customer_Old> customer_olds = new ArrayList<>();
        Customer_Old customer_old = new Customer_Old();
        customer_old.setDebit("4200");
        customer_old.setIndebt(false);
        customer_old.setBankAccount_old(new BankAccount_Old("1234-Duckburg", 49201));
        customer_old.setCustomer_address_old(new Customer_Address_Old("CapitolHill:Washington:USA", "42992", "ACME"));
        customer_olds.add(customer_old);
        Customer_Old customer_old2 = new Customer_Old();
        customer_old2.setDebit("0");
        customer_old2.setIndebt(true);
        customer_old2.setBankAccount_old(new BankAccount_Old("9329-Truckistan", 12));
        customer_old2.setCustomer_address_old(new Customer_Address_Old("Trucksters:Trekkietown:Mars", "42992", "Mars Corporation"));
        customer_olds.add(customer_old2);
        Customer_Old customer_old3= new Customer_Old();
        customer_old3.setDebit("0");
        customer_old3.setIndebt(true);
        customer_old3.setCustomer_address_old(new Customer_Address_Old("Trucksters:Trekkietown:Mars", "42992", "Mars Corporation"));
        customer_olds.add(customer_old3);
        return customer_olds;
    }
}
