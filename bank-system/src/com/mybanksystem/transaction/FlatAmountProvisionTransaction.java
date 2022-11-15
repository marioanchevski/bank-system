package com.mybanksystem.transaction;

public class FlatAmountProvisionTransaction extends Transaction {
    private double flatAmount;

    public FlatAmountProvisionTransaction(long bankId, long fromId, long toId, double amount, String description, double flatAmount, TransactionType type) {
        super(bankId, fromId, toId, amount, description, type);
        this.flatAmount = flatAmount;
    }

    @Override
    public double getProvision() {
        return flatAmount;
    }


}
