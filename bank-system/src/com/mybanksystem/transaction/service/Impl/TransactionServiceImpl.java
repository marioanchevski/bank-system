package com.mybanksystem.transaction.service.Impl;

import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.exceptions.NonExistentBankException;
import com.mybanksystem.bank.service.FindBankService;
import com.mybanksystem.transaction.*;
import com.mybanksystem.transaction.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final FindBankService findBankService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, FindBankService findBankService) {
        this.transactionRepository = transactionRepository;
        this.findBankService = findBankService;
    }

    @Override
    public String createTransaction(TransactionContext context) throws NonExistentBankException {
        Bank bank = findBankService.findBankById(context.getBankId());
        Transaction transaction = null;
        if (context.getAmount() < bank.getThresholdAmount()) {
            transaction = new FlatAmountProvisionTransaction(
                    context.getBankId(),
                    context.getFromId(),
                    context.getToId(),
                    context.getAmount(),
                    "FlatAmount",
                    bank.getFlatFeeAmount(),
                    context.getType());

        }
        else {
            transaction = new FlatPercentProvisionTransaction(
                    context.getBankId(),
                    context.getFromId(),
                    context.getToId(),
                    context.getAmount(),
                    "FlatPercent",
                    bank.getPercentFeeAmount(),
                    context.getType());
        }
        transactionRepository.saveTransaction(transaction);
        return transaction.getId();
    }
}
