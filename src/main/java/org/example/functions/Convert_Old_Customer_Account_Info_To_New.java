package org.example.functions;

import org.example.dataobjects.Customer_Account_Info;
import org.example.dataobjects.Customer_Old;

import java.util.function.Function;

public interface Convert_Old_Customer_Account_Info_To_New {

    static Function<Customer_Old, Customer_Account_Info> exctract_customer_account_info_from_old_customer = customer_old -> new Customer_Account_Info(
            customer_old.getDebit(),
            customer_old.isIndebt());
}
