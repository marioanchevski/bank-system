package com.mybanksystem.account.service.Impl;

import com.mybanksystem.account.Account;
import com.mybanksystem.account.exceptions.NonExistentAccountException;
import com.mybanksystem.account.service.AccountPrintingService;
import com.mybanksystem.account.service.FindAccountService;
import com.mybanksystem.account.service.FindTransactionsForAccountService;
import com.mybanksystem.bank.BankRepository;
import com.mybanksystem.transaction.Transaction;
import com.mybanksystem.transaction.service.TransactionPrintingService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountPrintingServiceImpl implements AccountPrintingService {

    private final FindAccountService findAccountService;
    private final TransactionPrintingService transactionPrintingService;
    private final FindTransactionsForAccountService findTransactionsForAccountService;

    public AccountPrintingServiceImpl(FindAccountService findAccountService,
                                      TransactionPrintingService transactionPrintingService,
                                      FindTransactionsForAccountService findTransactionsForAccountService) {
        this.findAccountService = findAccountService;
        this.transactionPrintingService = transactionPrintingService;
        this.findTransactionsForAccountService = findTransactionsForAccountService;
    }

    @Override
    public void printAccountDetails(Long accountId) throws NonExistentAccountException {
        Account account = findAccountService.findAccountById(accountId).orElseThrow(() -> new NonExistentAccountException(accountId));
        List<Transaction> transactionList = findTransactionsForAccountService.findAllTransactionsForAccount(accountId);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Account ID: %d\n", account.getId()));
        stringBuilder.append(String.format("Owner: %s\n", account.getName()));
        stringBuilder.append(String.format("Balance: %.2f$\n", account.getBalance()));
        stringBuilder.append("Transactions:\n");
        transactionList.forEach(transaction -> stringBuilder.append(transactionPrintingService.printTransaction(transaction.getId())));
        System.out.println(stringBuilder);
    }

}
