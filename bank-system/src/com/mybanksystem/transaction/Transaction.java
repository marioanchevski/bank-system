package com.mybanksystem.transaction;

public abstract class Transaction {
    private long fromId;
    private long toId;
    private double amount;
    private String description;
    private TransactionType type;

    public Transaction(long fromId, long toId, double amount, String description, TransactionType type) {
        this.fromId = fromId;
        this.toId = toId;
        this.amount = amount;
        this.description = description;
        this.type = type;
    }

    public long getFromId() {
        return fromId;
    }

    public long getToId() {
        return toId;
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

}
