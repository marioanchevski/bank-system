package com.mybanksystem.account.service;

import com.mybanksystem.transaction.Transaction;

import java.util.List;

public interface FindTransactionsForAccountService {
    List<Transaction> findAllTransactionsForAccount(Long accountId);
}
