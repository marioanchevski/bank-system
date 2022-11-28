package com.mybanksystem.transaction;

import com.mybanksystem.account.Account;
import com.mybanksystem.bank.Bank;
import lombok.Data;

@Data
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

    public abstract double getProvision();

}
