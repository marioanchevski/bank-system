package com.mybanksystem.account.service.Impl;

import com.mybanksystem.account.model.Account;
import com.mybanksystem.account.JpaAccountRepository;
import com.mybanksystem.account.service.AccountService;
import com.mybanksystem.transaction.JpaTransactionRepository;
import com.mybanksystem.transaction.model.entity.Transaction;
import com.mybanksystem.transaction.model.enumeration.TransactionType;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final JpaTransactionRepository transactionRepository;
    private final JpaAccountRepository accountRepository;

    public AccountServiceImpl(JpaTransactionRepository transactionRepository,
                              JpaAccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void updateAccounts(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).get();
        Account accountFrom = transaction.getAccountFrom();
        Account accountTo = transaction.getAccountTo();

        if (transaction.getType().equals(TransactionType.DEPOSIT)) {
            accountFrom.setBalance(accountFrom.getBalance() - (transaction.getAmount() * -1 + transaction.getProvision()));
        } else if (transaction.getType().equals(TransactionType.WITHDRAW)) {
            accountFrom.setBalance(accountFrom.getBalance() - (transaction.getAmount() + transaction.getProvision()));
        } else {
            accountFrom.setBalance(accountFrom.getBalance() - (transaction.getAmount() + transaction.getProvision()));
            accountTo.setBalance(accountTo.getBalance() + transaction.getAmount());
            accountRepository.save(accountTo);
        }
        accountRepository.save(accountFrom);

    }


}
