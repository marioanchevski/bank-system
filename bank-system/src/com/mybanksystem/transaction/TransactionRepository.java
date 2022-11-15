package com.mybanksystem.transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TransactionRepository {
    private Map<String, Transaction> transactionData;

    public TransactionRepository() {
        transactionData = new HashMap<>();
    }

    public void saveTransaction(Transaction transaction) {
        transactionData.put(generateTransactionId(), transaction);
    }

    public Transaction findTransactionById(String transactionId) {
        return transactionData.get(transactionId);
    }

    private String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
}
