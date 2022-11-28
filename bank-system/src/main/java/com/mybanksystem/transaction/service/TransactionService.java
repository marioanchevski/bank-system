package com.mybanksystem.transaction.service;

import com.mybanksystem.util.Bean;
import com.mybanksystem.bank.exceptions.NonExistentBankException;
import com.mybanksystem.transaction.TransactionContext;

public interface TransactionService extends Bean {
    String createTransaction(TransactionContext context) throws NonExistentBankException;
}
