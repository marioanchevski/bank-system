package com.mybanksystem;

import com.mybanksystem.Transaction;
import com.mybanksystem.TransactionType;

public class FlatPercentProvisionTransaction extends Transaction {
    private int flatPercent;

    public FlatPercentProvisionTransaction(long fromId, long toId, double amount, String description, int flatPercent, TransactionType type) {
        super(fromId, toId, amount, description, type);
        this.flatPercent = flatPercent;
    }

    @Override
    double getProvision() {
        return getAmount() * (flatPercent / 100.0);
    }
}
