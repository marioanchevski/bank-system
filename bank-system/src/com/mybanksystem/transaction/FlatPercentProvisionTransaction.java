package com.mybanksystem.transaction;

public class FlatPercentProvisionTransaction extends Transaction {
    private int flatPercent;

    public FlatPercentProvisionTransaction(long bankId, long fromId, long toId, double amount, String description, int flatPercent, TransactionType type) {
        super(bankId, fromId, toId, amount, description, type);
        this.flatPercent = flatPercent;
    }

    @Override
    public double getProvision() {
        return getAmount() * (flatPercent / 100.0);
    }
}
