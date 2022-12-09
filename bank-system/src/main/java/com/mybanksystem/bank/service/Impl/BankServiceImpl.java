package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.account.service.AccountService;
import com.mybanksystem.account.model.exceptions.ZeroAmountException;
import com.mybanksystem.bank.model.entity.Bank;
import com.mybanksystem.bank.model.entity.BankTransferDetails;
import com.mybanksystem.bank.repository.JpaBankRepository;
import com.mybanksystem.bank.repository.JpaBankTransferDetailsRepository;
import com.mybanksystem.bank.model.exceptions.NonExistentBankException;
import com.mybanksystem.bank.service.BankService;
import com.mybanksystem.transaction.*;
import com.mybanksystem.account.model.Account;
import com.mybanksystem.account.model.exceptions.InsufficientFundsException;
import com.mybanksystem.transaction.model.entity.Transaction;
import com.mybanksystem.transaction.model.enumeration.TransactionType;
import org.springframework.stereotype.Service;


@Service
public class BankServiceImpl implements BankService {
    private final JpaTransactionRepository transactionRepository;
    private final JpaBankRepository bankRepository;
    private final AccountService accountService;

    private final JpaBankTransferDetailsRepository bankTransferDetailsRepository;

    public BankServiceImpl(JpaTransactionRepository transactionRepository, JpaBankRepository bankRepository, AccountService accountService, JpaBankTransferDetailsRepository bankTransferDetailsRepository) {
        this.transactionRepository = transactionRepository;
        this.bankRepository = bankRepository;
        this.accountService = accountService;
        this.bankTransferDetailsRepository = bankTransferDetailsRepository;
    }

    @Override
    public void makeTransaction(Long transactionId, Long bankId) throws InsufficientFundsException, NonExistentBankException {
        Bank bank = bankRepository.findById(bankId).get();
        Transaction transaction = transactionRepository.findById(transactionId).get();
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
        BankTransferDetails bankTransferDetails = bankTransferDetailsRepository.findByBank(bank);
        bankTransferDetails.setTotalTransferAmount(bankTransferDetails.getTotalTransferAmount() + transaction.getAmount());
        bankTransferDetails.setTotalTransactionFeeAmount(bankTransferDetails.getTotalTransactionFeeAmount() + transaction.getProvision());
/*        bank.setTotalTransferAmount(bank.getTotalTransferAmount() + transaction.getAmount());
        bank.setTotalTransactionFeeAmount(bank.getTotalTransactionFeeAmount() + transaction.getProvision());*/

        //bank.getBankTransactions().add(transaction);
        //bankRepository.save(bank);
        bankTransferDetailsRepository.save(bankTransferDetails);

    }


}
