package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.account.service.AccountService;
import com.mybanksystem.account.exceptions.ZeroAmountException;
import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.exceptions.NonExistentBankException;
import com.mybanksystem.bank.service.BankService;
import com.mybanksystem.bank.service.FindBankService;
import com.mybanksystem.transaction.*;
import com.mybanksystem.account.Account;
import com.mybanksystem.account.exceptions.InsufficientFundsException;
import org.springframework.stereotype.Service;


@Service
public class BankServiceImpl implements BankService {
    private final TransactionRepository transactionRepository;
    private final FindBankService findBankService;
    private final AccountService accountService;

    public BankServiceImpl(TransactionRepository transactionRepository, FindBankService findBankService, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.findBankService = findBankService;
        this.accountService = accountService;
    }

    @Override
    public void makeTransaction(String transactionId, Long bankId) throws InsufficientFundsException, NonExistentBankException {
        Bank bank = findBankService.findBankById(bankId);
        Transaction transaction = transactionRepository.findTransactionById(transactionId);
        Account fromAccount = transaction.getAccountFrom();

        double totalTransferAmount = transaction.getAmount() + transaction.getProvision();

        if (transaction.getAmount() == 0)
            throw new ZeroAmountException();

        // should there be provision if you try to deposit to your account
        if (transaction.getType().equals(TransactionType.DEPOSIT)) {
            if (fromAccount.getBalance() + transaction.getAmount() < transaction.getProvision()) {
                throw new InsufficientFundsException(transaction.getAmount());
            }
        } else if (transaction.getType().equals(TransactionType.WITHDRAW)) {
            if (fromAccount.getBalance() < totalTransferAmount) {
                throw new InsufficientFundsException(fromAccount.getBalance(), transaction.getAmount());
            }
        } else {
            if (fromAccount.getBalance() < totalTransferAmount) {
                throw new InsufficientFundsException(fromAccount.getBalance(), transaction.getAmount());
            }
        }

        accountService.updateAccounts(transactionId);
        bank.setTotalTransferAmount(bank.getTotalTransferAmount() + transaction.getAmount());
        bank.setTotalTransactionFeeAmount(bank.getTotalTransactionFeeAmount() + transaction.getProvision());
        bank.getBankTransactions().add(transaction);

    }


}
