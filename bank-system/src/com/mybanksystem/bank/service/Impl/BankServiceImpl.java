package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.account.service.AccountService;
import com.mybanksystem.account.exceptions.ZeroAmountException;
import com.mybanksystem.account.service.FindAccountService;
import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.exceptions.NonExistentBankException;
import com.mybanksystem.bank.service.BankService;
import com.mybanksystem.bank.service.FindBankService;
import com.mybanksystem.transaction.*;
import com.mybanksystem.account.Account;
import com.mybanksystem.account.exceptions.InsufficientFundsException;
import com.mybanksystem.account.exceptions.NonExistentAccountException;


public class BankServiceImpl implements BankService {
    private final TransactionRepository transactionRepository;
    private final FindBankService findBankService;
    private final AccountService accountService;
    private final FindAccountService findAccountService;

    public BankServiceImpl(TransactionRepository transactionRepository, FindBankService findBankService, AccountService accountService, FindAccountService findAccountService) {
        this.transactionRepository = transactionRepository;
        this.findBankService = findBankService;
        this.accountService = accountService;
        this.findAccountService = findAccountService;
    }

    @Override
    public void makeTransaction(String transactionId, Long bankId) throws InsufficientFundsException, NonExistentAccountException, NonExistentBankException {
        Bank bank = findBankService.findBankById(bankId);
        Transaction transaction = transactionRepository.findTransactionById(transactionId);
        Account fromAccount = findAccountService.findAccountById(transaction.getFromId())
                .orElseThrow(() -> new NonExistentAccountException(transaction.getFromId()));

        double totalTransferAmount = transaction.getAmount() + transaction.getProvision();

        if (transaction.getAmount() == 0)
            throw new ZeroAmountException();

        // should there be provision if you try to deposit to your account
        if (transaction.getType().equals(TransactionType.DEPOSIT)) {
            if (fromAccount.getBalance() + transaction.getAmount() < transaction.getProvision())
                throw new InsufficientFundsException(transaction.getAmount());
        } else if (transaction.getType().equals(TransactionType.WITHDRAW)) {
            if (fromAccount.getBalance() < totalTransferAmount)
                throw new InsufficientFundsException(fromAccount.getBalance(), transaction.getAmount());
        } else {
            if (fromAccount.getBalance() < totalTransferAmount)
                throw new InsufficientFundsException(fromAccount.getBalance(), transaction.getAmount());
        }

        accountService.updateAccounts(transactionId);
        bank.setTotalTransferAmount(bank.getTotalTransferAmount() + transaction.getAmount());
        bank.setTotalTransactionFeeAmount(bank.getTotalTransactionFeeAmount() + transaction.getProvision());
        bank.getBankTransactions().add(transaction);

    }




}
