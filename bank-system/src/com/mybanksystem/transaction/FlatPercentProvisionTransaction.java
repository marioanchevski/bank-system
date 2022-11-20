package com.mybanksystem.transaction;

import com.mybanksystem.account.Account;
import com.mybanksystem.bank.Bank;

public class FlatPercentProvisionTransaction extends Transaction {
    private int flatPercent;


    // Account accountFrom, Account accountTo, Double amount, String description, TransactionType type, Bank bank
    // long bankId, long fromId, long toId, double amount, String description, double flatAmount, TransactionType type
    public FlatPercentProvisionTransaction(Account accountFrom, Account accountTo, Double amount, String description, TransactionType type, Bank bank) {
        super(accountFrom, accountTo, amount, description, type, bank);
        this.flatPercent = flatPercent;
    }

    @Override
    public double getProvision() {
        return getAmount() * (flatPercent / 100.0);
    }
}
