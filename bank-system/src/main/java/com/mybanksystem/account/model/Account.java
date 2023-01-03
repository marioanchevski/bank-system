package com.mybanksystem.account.model;

import com.mybanksystem.bank.model.entity.Bank;

import javax.persistence.*;
import java.math.BigDecimal;

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

    private String UUID;
    private String name;
    private BigDecimal balance;
    @ManyToOne(fetch = FetchType.LAZY)
    private Bank bank;

    public Account(String name, BigDecimal balance) {
        this.id = null;
        this.name = name;
        this.balance = balance;
    }

    public Account(String name, BigDecimal balance, Bank bank) {
        this.name = name;
        this.balance = balance;
        this.bank = bank;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
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

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    @Override
    public String toString() {
        return String.format("AccountID: %d\tAccount owner: %-15s\tCurrent balance: %10.2f$", getId(), getName(), getBalance());
    }
}
