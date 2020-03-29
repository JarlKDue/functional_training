package org.example.functions;

import org.example.dataobjects.Customer_Address_New;
import org.example.dataobjects.Customer_Address_Old;

import java.util.function.Function;

public interface Convert_Old_Address_To_New {

    static Function<Customer_Address_Old, Customer_Address_New> convert_old_customer_adress_to_new_customer_adress = customer_address_old -> new Customer_Address_New(
            customer_address_old.getAddress().split(":")[0],
            customer_address_old.getAddress().split(":")[1],
            customer_address_old.getAddress().split(":")[2],
            customer_address_old.getName()
    );
}
