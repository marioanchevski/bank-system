package com.mybanksystem.account.service;

import com.mybanksystem.transaction.model.entity.Transaction;

import java.util.Collection;
import java.util.List;

public interface FindTransactionsForAccountService {
    List<Transaction> findAllTransactionsForAccount(Long accountId);
    Collection<Transaction> findAllTransactionsForAccount(String accountUUID);
}
