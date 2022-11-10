package com.mybanksystem.bank;

import com.mybanksystem.account.AccountService;
import com.mybanksystem.account.exceptions.ZeroAmountException;
import com.mybanksystem.transaction.Transaction;
import com.mybanksystem.transaction.TransactionType;
import com.mybanksystem.account.Account;
import com.mybanksystem.account.exceptions.InsufficientFundsException;
import com.mybanksystem.account.exceptions.NonExistentAccountException;
import java.util.Optional;

public class BankServiceImpl implements BankService {
    private final AccountService accountService;

    public BankServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void makeTransaction(Transaction transaction, Bank bank) throws InsufficientFundsException, NonExistentAccountException {
        Account fromAccount = accountService.findAccountById(transaction.getFromId(), bank)
                .orElseThrow(() -> new NonExistentAccountException(transaction.getFromId(), bank.getName()));
        Account toAccount = accountService.findAccountById(transaction.getToId(), bank)
                .orElseThrow(() -> new NonExistentAccountException(transaction.getFromId(), bank.getName()));

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

        accountService.updateAccounts(fromAccount, toAccount, transaction);
        bank.setTotalTransferAmount(bank.getTotalTransferAmount() + transaction.getAmount());
        bank.setTotalTransactionFeeAmount(bank.getTotalTransactionFeeAmount() + transaction.getProvision());
    }

    @Override
    public void addAccountToBank(Account account, Bank bank) {
        bank.getAccounts().add(account);
    }

    @Override
    public String getTotalTransactionFeeAmount(Bank bank) {
        return String.format("Total transaction fee: %10.2f$", bank.getTotalTransactionFeeAmount());
    }

    @Override
    public String getTotalTotalTransferAmount(Bank bank) {
        return String.format("Total transfer amount: %10.2f$", bank.getTotalTransferAmount());

    }

    @Override
    public String printBank(Bank bank) {
        return String.format("Bank: %s\nFlatFee: %.2f$ \nPercentFee: %d%%\nThresholdAmount: %.2f$\n",
                bank.getName(), bank.getFlatFeeAmount(), bank.getPercentFeeAmount(), bank.getThresholdAmount());
    }

    @Override
    public boolean hasAccounts(Bank bank) {
        return !bank.getAccounts().isEmpty();
    }

    public Optional<Bank> findBankById(Long id) {
        return Optional.empty();
    }
}
