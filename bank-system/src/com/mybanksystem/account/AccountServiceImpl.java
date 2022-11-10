package com.mybanksystem.account;

import java.util.Optional;
import java.util.stream.Collectors;

import com.mybanksystem.account.exceptions.NonExistentAccountException;
import com.mybanksystem.bank.Bank;
import com.mybanksystem.transaction.Transaction;
import com.mybanksystem.transaction.TransactionType;

public class AccountServiceImpl implements AccountService {

    @Override
    public Optional<Account> findAccountById(Long id, Bank bank) {
        return bank.getAccounts().stream()
                .filter(account -> account.getId().equals(id))
                .findFirst();
    }

    @Override
    public void updateAccounts(Account accountFrom, Account accountTo, Transaction transaction) {
        accountFrom.getTransactions().add(String.format("%s: %.2f to account: %d, transaction fee: %.2f %s",
                transaction.getType(),
                transaction.getAmount(),
                accountFrom.getId(),
                transaction.getProvision(),
                transaction.getDescription()));

        if (transaction.getType().equals(TransactionType.DEPOSIT))
            accountFrom.setBalance(accountFrom.getBalance() - (transaction.getAmount() * -1 + transaction.getProvision()));
        else if (transaction.getType().equals(TransactionType.WITHDRAW))
            accountFrom.setBalance(accountFrom.getBalance() - (transaction.getAmount() + transaction.getProvision()));
        else {
            accountFrom.setBalance(accountFrom.getBalance() - (transaction.getAmount() + transaction.getProvision()));
            accountTo.setBalance(accountTo.getBalance() + transaction.getAmount());
            if (!accountFrom.getId().equals(accountTo.getId()))
                accountTo.getTransactions().add(String.format("RECEIVED: %.2f from account: %d", transaction.getAmount(), accountFrom.getId()));
        }

    }

    @Override
    public void getAccountDetails(Long accountId, Bank bank) throws NonExistentAccountException {
        if (findAccountById(accountId, bank).isEmpty())
            throw new NonExistentAccountException(accountId, bank.getName());
        printDetailedAccount(findAccountById(accountId, bank).get());
    }

    @Override
    public void printDetailedAccount(Account account) {
        String accountInformation = String.format("Account ID: %d", account.getId()) +
                String.format("\nBelongs to: %s", account.getName()) +
                String.format("\nBalance: %.2f$", account.getBalance()) +
                String.format("\nTransactions:\n%s", String.join("\n", account.getTransactions()));
        System.out.println(accountInformation);
    }

    @Override
    public void printAccounts(Bank bank) {
        String print = bank.getAccounts().stream().map(this::printAccount).collect(Collectors.joining("\n"));
        System.out.println(print);
    }

    @Override
    public String printAccount(Account account) {
        return String.format("AccountID: %d\tAccount owner: %-15s\tCurrent balance: %10.2f$", account.getId(), account.getName(), account.getBalance());
    }

}
