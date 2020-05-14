package org.example.functions;

import org.example.dataobjects.BankAccount_New;
import org.example.dataobjects.BankAccount_Old;

import java.util.Optional;
import java.util.function.Function;

public interface Convert_Old_BankAccount_To_New {

    static Function<BankAccount_Old, Optional<BankAccount_New>> convert_old_bankaccount_to_new_bankaccount = bankAccount_old -> Optional.of(new BankAccount_New(
            bankAccount_old.getBankAccountIdentifier().split("-")[0],
            bankAccount_old.getBankAccountIdentifier().split("-")[1],
            bankAccount_old.getAmount()));

}
