package com.mybanksystem.transaction.service;

import com.mybanksystem.account.model.exceptions.NonExistentAccountException;
import com.mybanksystem.bank.model.exceptions.NonExistentBankException;
import com.mybanksystem.transaction.model.TransactionContext;

public interface TransactionService {
    Long createTransaction(TransactionContext context) throws NonExistentBankException, NonExistentAccountException;
}
