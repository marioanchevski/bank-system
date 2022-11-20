package com.mybanksystem.account.service.Impl;

import com.mybanksystem.account.Account;
import com.mybanksystem.account.exceptions.NonExistentAccountException;
import com.mybanksystem.account.service.AccountService;
import com.mybanksystem.account.service.FindAccountService;
import com.mybanksystem.transaction.Transaction;
import com.mybanksystem.transaction.TransactionRepository;
import com.mybanksystem.transaction.TransactionType;

public class AccountServiceImpl implements AccountService {
    private final FindAccountService findAccountService;
    private final TransactionRepository transactionRepository;

    public AccountServiceImpl(FindAccountService findAccountService, TransactionRepository transactionRepository) {
        this.findAccountService = findAccountService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void updateAccounts(String transactionId) throws NonExistentAccountException {
        Transaction transaction = transactionRepository.findTransactionById(transactionId);
        Account accountFrom = findAccountService.findAccountById(transaction.getFromId())
                .orElseThrow(()-> new NonExistentAccountException(transaction.getFromId()));
        Account accountTo = findAccountService.findAccountById(transaction.getToId())
                .orElseThrow(()-> new NonExistentAccountException(transaction.getToId()));


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
