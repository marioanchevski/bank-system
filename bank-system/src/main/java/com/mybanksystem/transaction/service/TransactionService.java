package com.mybanksystem.transaction.service;

import com.mybanksystem.transaction.model.dto.TransactionDTO;
import com.mybanksystem.transaction.model.enumeration.TransactionType;

public interface TransactionService {
    String createTransaction(TransactionDTO context, TransactionType transactionType);
}
