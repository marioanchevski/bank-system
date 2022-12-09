package com.mybanksystem.account.model;

import com.mybanksystem.bank.model.entity.Bank;

import javax.persistence.*;

@Entity
public class Account {
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "ACC_SEQ",
            initialValue = 10000,
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            generator = "account_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String name;
    private Double balance;
    @ManyToOne(fetch = FetchType.LAZY)
    private Bank bank;

    public Account(String name, double balance) {
        this.id = null;
        this.name = name;
        this.balance = balance;
    }

    public Account(String name, Double balance, Bank bank) {
        this.name = name;
        this.balance = balance;
        this.bank = bank;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bank getBank() {
        return bank;
    }

    @Override
    public String toString() {
        return String.format("AccountID: %d\tAccount owner: %-15s\tCurrent balance: %10.2f$", getId(), getName(), getBalance());
    }
}
