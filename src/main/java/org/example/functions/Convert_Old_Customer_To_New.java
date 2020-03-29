package org.example.functions;

import org.example.dataobjects.BankAccount_New;
import org.example.dataobjects.Customer_New;
import org.example.dataobjects.Customer_Old;

import java.util.function.Function;

import static org.example.functions.Convert_Old_Address_To_New.convert_old_customer_adress_to_new_customer_adress;
import static org.example.functions.Convert_Old_BankAccount_To_New.convert_old_bankaccount_to_new_bankaccount;
import static org.example.functions.Convert_Old_Customer_Account_Info_To_New.exctract_customer_account_info_from_old_customer;

public interface Convert_Old_Customer_To_New {

    static Function<Customer_Old, Customer_New> convert_old_customer_to_new_customer = customer_old -> new Customer_New(
           convert_old_bankaccount_to_new_bankaccount.apply(customer_old.getBankAccount_old()),
           convert_old_customer_adress_to_new_customer_adress.apply(customer_old.getCustomer_address_old()),
           exctract_customer_account_info_from_old_customer.apply(customer_old));

}
