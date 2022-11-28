package com.mybanksystem.transaction;

import com.mybanksystem.account.Account;
import com.mybanksystem.bank.Bank;

public class FlatAmountProvisionTransaction extends Transaction {
    private double flatAmount;

    public FlatAmountProvisionTransaction(Account accountFrom, Account accountTo, Double amount, String description, TransactionType type, Bank bank) {
        super(accountFrom, accountTo, amount, description, type, bank);
        this.flatAmount = bank.getFlatFeeAmount();
    }

    @Override
    public double getProvision() {
        return flatAmount;
    }


}
