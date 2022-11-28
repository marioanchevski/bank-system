package com.mybanksystem.account;

import lombok.Data;

@Data
public class Account {
    private Long id;
    private String name;
    private Double balance;

    public Account(String name, double balance) {
        this.id = null;
        this.name = name;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("AccountID: %d\tAccount owner: %-15s\tCurrent balance: %10.2f$", getId(), getName(), getBalance());
    }
}
