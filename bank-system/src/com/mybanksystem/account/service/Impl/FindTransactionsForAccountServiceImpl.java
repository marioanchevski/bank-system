package com.mybanksystem.account.service.Impl;

import com.mybanksystem.account.service.FindTransactionsForAccountService;
import com.mybanksystem.transaction.Transaction;
import com.mybanksystem.transaction.TransactionRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FindTransactionsForAccountServiceImpl implements FindTransactionsForAccountService {
    private final TransactionRepository transactionRepository;

    public FindTransactionsForAccountServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAllTransactionsForAccount(Long accountId) {
        return transactionRepository.getAllTransactions()
                .stream()
                .filter(transaction -> transaction.getAccountFrom().getId().equals(accountId) ||
                        transaction.getAccountTo().getId().equals(accountId))
                .collect(Collectors.toList());
    }
}
