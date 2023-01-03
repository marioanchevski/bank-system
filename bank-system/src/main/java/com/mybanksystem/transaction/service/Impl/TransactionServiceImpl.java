package com.mybanksystem.transaction.service.Impl;

import com.mybanksystem.account.JpaAccountRepository;
import com.mybanksystem.account.model.exceptions.NonExistentAccountException;
import com.mybanksystem.account.service.AccountService;
import com.mybanksystem.bank.model.exceptions.NonExistentBankException;
import com.mybanksystem.bank.repository.JpaBankConfigurationRepository;
import com.mybanksystem.bank.service.BankService;
import com.mybanksystem.transaction.*;
import com.mybanksystem.transaction.model.dto.TransactionDTO;
import com.mybanksystem.transaction.model.entity.FlatAmountProvisionTransaction;
import com.mybanksystem.transaction.model.entity.FlatPercentProvisionTransaction;
import com.mybanksystem.transaction.model.entity.Transaction;
import com.mybanksystem.transaction.model.enumeration.TransactionType;
import com.mybanksystem.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final JpaTransactionRepository transactionRepository;
    private final JpaAccountRepository accountRepository;
    private final BankService bankService;
    private final JpaBankConfigurationRepository bankConfigurationRepository;
    private final AccountService accountService;

    @Override
    @Transactional
    public String createTransaction(TransactionDTO context, TransactionType transactionType) {

        Transaction transaction = resolveTransactionType(context, transactionType);
        transactionRepository.save(transaction);

        bankService.checkValidAmount(transaction.getUUID());

        accountService.updateAccounts(transaction.getUUID());

        return transaction.getUUID();
    }

    private Transaction resolveTransactionType(TransactionDTO context, TransactionType transactionType) {


        var accountFrom = accountRepository.findAccountByUUID(context.getAccountFromUUID())
                .orElseThrow(() -> new NonExistentAccountException(context.getAccountFromUUID()));

        var accountTo = accountRepository.findAccountByUUID(context.getAccountToUUID())
                .orElseThrow(() -> new NonExistentAccountException(context.getAccountToUUID()));

        var bankConfiguration = bankConfigurationRepository.findByBankUUID(accountFrom.getBank().getUUID())
                .orElseThrow(() -> new NonExistentBankException(accountFrom.getBank().getUUID()));

        Transaction transaction;
        if (BigDecimal.valueOf(context.getAmount()).compareTo(bankConfiguration.getThresholdAmount()) < 0) {
            transaction = new FlatAmountProvisionTransaction(
                    accountFrom,
                    accountTo,
                    BigDecimal.valueOf(context.getAmount()),
                    "FlatAmount",
                    transactionType,
                    bankConfiguration.getFlatFeeAmount()
            );

        } else {
            transaction = new FlatPercentProvisionTransaction(
                    accountFrom,
                    accountTo,
                    BigDecimal.valueOf(context.getAmount()),
                    "FlatPercent",
                    transactionType,
                    bankConfiguration.getPercentFeeAmount()
            );
        }

        UUID uuid = UUID.randomUUID();
        transaction.setUUID(uuid.toString());
        return transaction;
    }
}
