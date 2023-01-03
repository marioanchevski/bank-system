package com.mybanksystem.account.service.Impl;

import com.mybanksystem.account.service.FindTransactionsForAccountService;
import com.mybanksystem.transaction.JpaTransactionRepository;
import com.mybanksystem.transaction.model.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class FindTransactionsForAccountServiceImpl implements FindTransactionsForAccountService {
    private final JpaTransactionRepository transactionRepository;

    public FindTransactionsForAccountServiceImpl(JpaTransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAllTransactionsForAccount(Long accountId) {
        return transactionRepository.findAll()
                .stream()
                .filter(transaction -> transaction.getAccountFrom().getId().equals(accountId) ||
                        transaction.getAccountTo().getId().equals(accountId))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Transaction> findAllTransactionsForAccount(String accountUUID) {
        return transactionRepository.findAllForAccount(accountUUID);
    }
}
