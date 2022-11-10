package com.mybanksystem.account;

import com.mybanksystem.account.exceptions.NonExistentAccountException;
import com.mybanksystem.bank.Bank;
import com.mybanksystem.transaction.Transaction;

import java.util.Optional;


public interface AccountService {
    Optional<Account> findAccountById(Long id, Bank bank);

    void updateAccounts(Account accountFrom, Account accountTo, Transaction transaction);

    void getAccountDetails(Long accountId, Bank bank) throws NonExistentAccountException;

    void printDetailedAccount(Account account);

    String printAccount(Account account);

    void printAccounts(Bank bank);
}
