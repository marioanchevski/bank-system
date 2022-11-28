package com.mybanksystem.account.service;

import com.mybanksystem.util.Bean;
import com.mybanksystem.transaction.Transaction;

import java.util.List;

public interface FindTransactionsForAccountService extends Bean {
    List<Transaction> findAllTransactionsForAccount(Long accountId);
}
