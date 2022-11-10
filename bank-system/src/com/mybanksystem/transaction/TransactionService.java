package com.mybanksystem.transaction;

import com.mybanksystem.bank.Bank;

public interface TransactionService {
    Transaction getTransactionInstance(TransactionType type, long fromId, long toId, double amount, Bank bank);
}
