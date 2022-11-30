package com.mybanksystem.transaction;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TransactionRepository {
    private Map<String, Transaction> transactionData;

    public TransactionRepository() {
        transactionData = new HashMap<>();
    }

    public void saveTransaction(Transaction transaction) {
        String transactionId = generateTransactionId();
        transaction.setId(transactionId);
        transactionData.put(transactionId, transaction);
    }

    public Transaction findTransactionById(String transactionId) {
        return transactionData.get(transactionId);
    }

    private String generateTransactionId() {
        return UUID.randomUUID().toString();
    }

    public List<Transaction> getAllTransactions(){
        return new ArrayList<>(transactionData.values());
    }
}
