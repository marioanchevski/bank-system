package com.mybanksystem.transaction.service;

import com.mybanksystem.transaction.TransactionContext;

public interface TransactionService {
    String createTransaction(TransactionContext context);
}
