package com.mybanksystem.account;

public class Account {
    private Long id;
    private String name;
    private Double balance;

    public Account(String name, double balance) {
        this.id = null;
        this.name = name;
        this.balance = balance;
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
}
