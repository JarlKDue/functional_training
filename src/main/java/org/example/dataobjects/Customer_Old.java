package org.example.dataobjects;

public class Customer_Old {
    BankAccount_Old bankAccount_old = new BankAccount_Old();
    Customer_Address_Old customer_address_old = new Customer_Address_Old();
    String debit;
    boolean indebt;

    public String getDebit() {
        return debit;
    }

    public boolean isIndebt(){
        return indebt;
    }

    public BankAccount_Old getBankAccount_old() {
        return bankAccount_old;
    }

    public Customer_Address_Old getCustomer_address_old() {
        return customer_address_old;
    }

    public void setBankAccount_old(BankAccount_Old bankAccount_old) {
        this.bankAccount_old = bankAccount_old;
    }

    public void setCustomer_address_old(Customer_Address_Old customer_address_old) {
        this.customer_address_old = customer_address_old;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public void setIndebt(boolean indebt) {
        this.indebt = indebt;
    }
}
