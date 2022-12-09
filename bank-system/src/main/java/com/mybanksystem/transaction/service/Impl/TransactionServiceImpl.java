package com.mybanksystem.transaction.service.Impl;

import com.mybanksystem.account.model.Account;
import com.mybanksystem.account.model.exceptions.NonExistentAccountException;
import com.mybanksystem.account.service.FindAccountService;
import com.mybanksystem.bank.model.entity.Bank;
import com.mybanksystem.bank.model.entity.BankConfiguration;
import com.mybanksystem.bank.model.exceptions.NonExistentBankException;
import com.mybanksystem.bank.repository.JpaBankConfigurationRepository;
import com.mybanksystem.bank.service.FindBankService;
import com.mybanksystem.transaction.*;
import com.mybanksystem.transaction.model.entity.FlatAmountProvisionTransaction;
import com.mybanksystem.transaction.model.entity.FlatPercentProvisionTransaction;
import com.mybanksystem.transaction.model.entity.Transaction;
import com.mybanksystem.transaction.model.TransactionContext;
import com.mybanksystem.transaction.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final JpaTransactionRepository transactionRepository;
    private final FindBankService findBankService;
    private final FindAccountService findAccountService;
private final JpaBankConfigurationRepository bankConfigurationRepository;

    public TransactionServiceImpl(JpaTransactionRepository transactionRepository, FindBankService findBankService, FindAccountService findAccountService, JpaBankConfigurationRepository bankConfigurationRepository) {
        this.transactionRepository = transactionRepository;
        this.findBankService = findBankService;
        this.findAccountService = findAccountService;
        this.bankConfigurationRepository = bankConfigurationRepository;
    }

    @Override
    public Long createTransaction(TransactionContext context) throws NonExistentBankException, NonExistentAccountException {
        Bank bank = findBankService.findBankById(context.getBankId());
        Account accountFrom = findAccountService.findAccountById(context.getFromId())
                .orElseThrow(()-> new NonExistentAccountException(context.getFromId()));
        Account accountTo = findAccountService.findAccountById(context.getToId())
                .orElseThrow(() -> new NonExistentAccountException(context.getToId()));

        BankConfiguration bankConfiguration = bankConfigurationRepository.findById(bank.getBankConfiguration().getId()).get();
        Transaction transaction = null; //bank.getThresholdAmount()
        if (context.getAmount() < -1) {
            transaction = new FlatAmountProvisionTransaction(
                    accountFrom,
                    accountTo,
                    context.getAmount(),
                    "FlatAmount",
                    context.getType()
                    ,bankConfiguration.getFlatFeeAmount()
            );

        } else {
            transaction = new FlatPercentProvisionTransaction(
                    accountFrom,
                    accountTo,
                    context.getAmount(),
                    "FlatPercent",
                    context.getType(),
                    context.getAmount() * (bankConfiguration.getPercentFeeAmount() / 100.0)
            );
        }
        transactionRepository.save(transaction);
        return transaction.getId();
    }
}
