package com.mybanksystem.account.service.Impl;

import com.mybanksystem.account.Account;
import com.mybanksystem.account.service.AccountService;
import com.mybanksystem.transaction.Transaction;
import com.mybanksystem.transaction.TransactionRepository;
import com.mybanksystem.transaction.TransactionType;

public class AccountServiceImpl implements AccountService {
    private final TransactionRepository transactionRepository;

    public AccountServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void updateAccounts(String transactionId) {
        Transaction transaction = transactionRepository.findTransactionById(transactionId);
        Account accountFrom = transaction.getAccountFrom();
        Account accountTo = transaction.getAccountTo();

        if (transaction.getType().equals(TransactionType.DEPOSIT))
            accountFrom.setBalance(accountFrom.getBalance() - (transaction.getAmount() * -1 + transaction.getProvision()));
        else if (transaction.getType().equals(TransactionType.WITHDRAW))
            accountFrom.setBalance(accountFrom.getBalance() - (transaction.getAmount() + transaction.getProvision()));
        else {
            accountFrom.setBalance(accountFrom.getBalance() - (transaction.getAmount() + transaction.getProvision()));
            accountTo.setBalance(accountTo.getBalance() + transaction.getAmount());
        }

    }


}
