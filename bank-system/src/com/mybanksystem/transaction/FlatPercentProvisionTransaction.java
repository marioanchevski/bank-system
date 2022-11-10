package com.mybanksystem.transaction;

public class FlatPercentProvisionTransaction extends Transaction {
    private int flatPercent;

    public FlatPercentProvisionTransaction(long fromId, long toId, double amount, String description, int flatPercent, TransactionType type) {
        super(fromId, toId, amount, description, type);
        this.flatPercent = flatPercent;
    }

    @Override
    public double getProvision() {
        return getAmount() * (flatPercent / 100.0);
    }
}
