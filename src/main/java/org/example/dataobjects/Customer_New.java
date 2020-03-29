package org.example.dataobjects;

public class Customer_New {
    BankAccount_New bankAccount_new;
    Customer_Address_New customer_address_new;
    Customer_Account_Info customer_account_info;
    public Customer_New(BankAccount_New bankAccount_new, Customer_Address_New customer_address_new, Customer_Account_Info customer_account_info){
        this.bankAccount_new = bankAccount_new;
        this.customer_account_info = customer_account_info;
        this.customer_address_new = customer_address_new;
    }
}
