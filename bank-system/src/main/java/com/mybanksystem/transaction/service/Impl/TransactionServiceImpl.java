package com.mybanksystem.transaction.service.Impl;

import com.mybanksystem.account.Account;
import com.mybanksystem.account.service.FindAccountService;
import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.exceptions.NonExistentBankException;
import com.mybanksystem.bank.service.FindBankService;
import com.mybanksystem.transaction.*;
import com.mybanksystem.transaction.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final FindBankService findBankService;
    private final FindAccountService findAccountService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, FindBankService findBankService, FindAccountService findAccountService) {
        this.transactionRepository = transactionRepository;
        this.findBankService = findBankService;
        this.findAccountService = findAccountService;
    }

    @Override
    public String createTransaction(TransactionContext context) throws NonExistentBankException {
        Bank bank = findBankService.findBankById(context.getBankId());
        Account accountFrom = findAccountService.findAccountById(context.getFromId()).get();
        Account accountTo = findAccountService.findAccountById(context.getToId()).get();
        Transaction transaction = null;
        if (context.getAmount() < bank.getThresholdAmount()) {
            transaction = new FlatAmountProvisionTransaction(
                    accountFrom,
                    accountTo,
                    context.getAmount(),
                    "FlatAmount",
                    context.getType(),
                    bank
            );

        } else {
            transaction = new FlatPercentProvisionTransaction(
                    accountFrom,
                    accountTo,
                    context.getAmount(),
                    "FlatPercent",
                    context.getType(),
                    bank
            );
        }
        transactionRepository.saveTransaction(transaction);
        return transaction.getId();
    }
}
