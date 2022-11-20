package com.mybanksystem.account.service.Impl;

import com.mybanksystem.account.Account;
import com.mybanksystem.account.exceptions.NonExistentAccountException;
import com.mybanksystem.account.service.AccountPrintingService;
import com.mybanksystem.account.service.FindAccountService;
import com.mybanksystem.account.service.FindTransactionsForAccountService;
import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.BankRepository;
import com.mybanksystem.bank.exceptions.NonExistentBankException;
import com.mybanksystem.transaction.Transaction;
import com.mybanksystem.transaction.service.TransactionPrintingService;

import java.util.List;
import java.util.stream.Collectors;

public class AccountPrintingServiceImpl implements AccountPrintingService {
    private final BankRepository bankRepository;
    private final FindAccountService findAccountService;
    private final TransactionPrintingService transactionPrintingService;
    private final FindTransactionsForAccountService findTransactionsForAccountService;

    public AccountPrintingServiceImpl(BankRepository bankRepository,
                                      FindAccountService findAccountService,
                                      TransactionPrintingService transactionPrintingService,
                                      FindTransactionsForAccountService findTransactionsForAccountService) {
        this.bankRepository = bankRepository;
        this.findAccountService = findAccountService;
        this.transactionPrintingService = transactionPrintingService;
        this.findTransactionsForAccountService = findTransactionsForAccountService;
    }

    @Override
    public void printAccountDetails(Long accountId) throws NonExistentAccountException {
        Account account = findAccountService.findAccountById(accountId).orElseThrow(() -> new NonExistentAccountException(accountId));
        List<Transaction> transactionList =  findTransactionsForAccountService.findAllTransactionsForAccount(accountId);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Account ID: %d", account.getId()));
        stringBuilder.append(String.format("Owner: %s", account.getName()));
        stringBuilder.append(String.format("\nBalance: %.2f$", account.getBalance()));
        stringBuilder.append("\nTransactions:\n");
        transactionList.forEach(transaction -> stringBuilder.append(transactionPrintingService.printTransaction(transaction.getId())));
        System.out.println(stringBuilder);
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
