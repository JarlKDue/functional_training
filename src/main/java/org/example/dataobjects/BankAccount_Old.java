package org.example.dataobjects;

public class BankAccount_Old {
    String bankAccountIdentifier;
    int amount;

    public BankAccount_Old(String bankAccountIdentifier, int amount){
        this.bankAccountIdentifier = bankAccountIdentifier;
        this.amount = amount;
    }

    public BankAccount_Old(){

    }

    public String getBankAccountIdentifier() {
        return bankAccountIdentifier;
    }

    public int getAmount() {
        return amount;
    }
}
