package com.mybanksystem.transaction;

import com.mybanksystem.account.Account;
import com.mybanksystem.bank.Bank;

public class FlatPercentProvisionTransaction extends Transaction {
    private int flatPercent;

    public FlatPercentProvisionTransaction(Account accountFrom, Account accountTo, Double amount, String description, TransactionType type, Bank bank) {
        super(accountFrom, accountTo, amount, description, type, bank);
        this.flatPercent = bank.getPercentFeeAmount();
    }

    @Override
    public double getProvision() {
        return getAmount() * (flatPercent / 100.0);
    }
}
