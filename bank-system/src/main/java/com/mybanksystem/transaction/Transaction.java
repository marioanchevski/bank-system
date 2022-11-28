package com.mybanksystem.transaction;

import com.mybanksystem.account.Account;
import com.mybanksystem.bank.Bank;

public abstract class Transaction {
    private String id;
    private Account accountFrom;
    private Account accountTo;
    private Double amount;
    private String description;
    private TransactionType type;
    private Bank bank;

    public Transaction(Account accountFrom, Account accountTo, Double amount, String description, TransactionType type, Bank bank) {
        this.id = null;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.bank = bank;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public abstract double getProvision();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
