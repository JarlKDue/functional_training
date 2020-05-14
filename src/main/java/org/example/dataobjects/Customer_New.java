package org.example.dataobjects;

import java.util.Optional;

public class Customer_New {
    BankAccount_New bankAccount_new;
    Customer_Address_New customer_address_new;
    Customer_Account_Info customer_account_info;

    public void setBankAccount_new(BankAccount_New bankAccount_new) {
        this.bankAccount_new = bankAccount_new;
    }

    public void setCustomer_account_info(Customer_Account_Info customer_account_info) {
        this.customer_account_info = customer_account_info;
    }

    public void setCustomer_address_new(Customer_Address_New customer_address_new) {
        this.customer_address_new = customer_address_new;
    }

    public Optional<BankAccount_New> getBankAccount_new() {
        return Optional.of(bankAccount_new);
    }

    public Optional<Customer_Account_Info> getCustomer_account_info() {
        return Optional.of(customer_account_info);
    }

    public Optional<Customer_Address_New> getCustomer_address_new() {
        return Optional.of(customer_address_new);
    }
}
