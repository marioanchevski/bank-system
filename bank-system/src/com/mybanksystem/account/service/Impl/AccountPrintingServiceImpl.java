package com.mybanksystem.account.service.Impl;

import com.mybanksystem.account.Account;
import com.mybanksystem.account.exceptions.NonExistentAccountException;
import com.mybanksystem.account.service.AccountPrintingService;
import com.mybanksystem.account.service.FindAccountService;
import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.BankRepository;
import com.mybanksystem.bank.exceptions.NonExistentBankException;

import java.util.stream.Collectors;

public class AccountPrintingServiceImpl implements AccountPrintingService {
    private final BankRepository bankRepository;
    private final FindAccountService findAccountService;

    public AccountPrintingServiceImpl(BankRepository bankRepository, FindAccountService findAccountService) {
        this.bankRepository = bankRepository;
        this.findAccountService = findAccountService;
    }

    @Override
    public void printAccountDetails(Long accountId) throws NonExistentAccountException {
        Account account = findAccountService.findAccountById(accountId).orElseThrow(() -> new NonExistentAccountException(accountId));
        String accountInformation = String.format("Account ID: %d", account.getId()) +
                String.format("\nBelongs to: %s", account.getName()) +
                String.format("\nBalance: %.2f$", account.getBalance()) +
                String.format("\nTransactions:\n%s", String.join("\n", account.getTransactions()));
        System.out.println(accountInformation);
    }

    @Override
    public void printAllAccountsInBank(Long bankId) throws NonExistentBankException {
        Bank bank = bankRepository.findBankById(bankId)
                .orElseThrow(()-> new NonExistentBankException(bankId));
        String print = bank.getAccounts().stream().map(this::getAccountPrintingFormat).collect(Collectors.joining("\n"));
        System.out.println(print);
    }

    private String getAccountPrintingFormat(Account account) {
        return String.format("AccountID: %d\tAccount owner: %-15s\tCurrent balance: %10.2f$", account.getId(), account.getName(), account.getBalance());
    }
}
