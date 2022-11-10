package com.mybanksystem.transaction;

import com.mybanksystem.bank.Bank;

public class TransactionServiceImpl implements TransactionService{
    @Override
    public Transaction getTransactionInstance(TransactionType type, long fromId, long toId, double amount, Bank bank) {
        if (amount < bank.getThresholdAmount())
            return new FlatAmountProvisionTransaction(fromId, toId, amount, "FlatAmount", bank.getFlatFeeAmount(), type);
        else
            return new FlatPercentProvisionTransaction(fromId, toId, amount, "FlatPercent", bank.getPercentFeeAmount(), type);
    }
}
