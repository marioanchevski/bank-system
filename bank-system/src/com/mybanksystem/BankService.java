package com.mybanksystem;

import com.mybanksystem.exceptions.InsufficientFundsException;
import com.mybanksystem.exceptions.NonExistentAccountException;

import java.util.Optional;
import java.util.stream.Collectors;

public class BankService {
    private AccountService accountService = new AccountService();

    // refactor this method
    // reason to long
    public void makeTransaction(Transaction transaction, Bank bank) throws InsufficientFundsException, NonExistentAccountException {
        Account fromAccount = accountService.findAccountById(transaction.getFromId(), bank)
                .orElseThrow(()-> new NonExistentAccountException(transaction.getFromId(), bank.getName()));
        Account toAccount = accountService.findAccountById(transaction.getToId(), bank)
                .orElseThrow(()-> new NonExistentAccountException(transaction.getFromId(), bank.getName()));

        double amountToTransfer = transaction.getAmount();
        double provision = transaction.getProvision();
        double fromAccountBalance = fromAccount.getBalance();
        double toAccountBalance = toAccount.getBalance();
        double totalTransferAmount = amountToTransfer + provision;

        if (amountToTransfer == 0)
            throw new InsufficientFundsException();

        if (fromAccount.getId().equals(toAccount.getId())) {
            if (transaction.getType().equals(TransactionType.DEPOSIT)) {
                // should there be provision if you try to deposit to your account
                if (fromAccountBalance + amountToTransfer < provision)
                    throw new InsufficientFundsException(amountToTransfer);
                accountService.updateAccount(fromAccount, toAccount, transaction);

            } else if (transaction.getType().equals(TransactionType.WITHDRAW))
                if (fromAccountBalance < totalTransferAmount)
                    throw new InsufficientFundsException(fromAccountBalance, amountToTransfer);

            accountService.updateAccount(fromAccount, toAccount, transaction);
        } else {
            if (fromAccountBalance < totalTransferAmount)
                throw new InsufficientFundsException(fromAccountBalance, amountToTransfer);
            accountService.updateAccount(fromAccount, toAccount, transaction);
        }

        bank.setTotalTransferAmount(bank.getTotalTransferAmount() + Math.abs(amountToTransfer));
        bank.setTotalTransactionFeeAmount(bank.getTotalTransactionFeeAmount() + provision);
    }

    public void getAccountDetails(long accountId, Bank bank) throws NonExistentAccountException {
        if (getAccount(accountId, bank).isEmpty())
            throw new NonExistentAccountException(accountId, bank.getName());
        printDetailedAccount(getAccount(accountId, bank).get());
    }

    private void printDetailedAccount(Account account) {
        String accountInformation = String.format("com.mybanksystem.Account ID: %d", account.getId()) +
                String.format("\nBelongs to: %s", account.getName()) +
                String.format("\nBalance: %.2f$", account.getBalance()) +
                String.format("\nTransactions:\n%s", String.join("\n", account.getTransactions()));
        System.out.println(accountInformation);
    }

    public String printAccount(Account account) {
        return String.format("AccountID: %d\tcom.mybanksystem.Account owner: %-15s\tCurrent balance: %10.2f$", account.getId(), account.getName(), account.getBalance());
    }

    private Optional<Account> getAccount(long id, Bank bank) {
        return bank.getAccounts().stream().filter(account -> account.getId() == id).findAny();
    }

    public void addAccount(Account account, Bank bank) {
        bank.getAccounts().add(account);
    }

    public void printAccounts(Bank bank) {
        String print = bank.getAccounts().stream().map(this::printAccount).collect(Collectors.joining("\n"));
        System.out.println(print);
    }

    public String getTotalTransactionFeeAmount(Bank bank) {
        return String.format("Total transaction fee: %10.2f$", bank.getTotalTransactionFeeAmount());
    }

    public String getTotalTotalTransferAmount(Bank bank) {
        return String.format("Total transfer amount: %10.2f$", bank.getTotalTransferAmount());

    }
    public String printBank(Bank bank) {
        return String.format("com.mybanksystem.Bank: %s\nFlatFee: %.2f$ \nPercentFee: %d%%\nThresholdAmount: %.2f$\n",
                bank.getName(), bank.getFlatFeeAmount(), bank.getPercentFeeAmount(), bank.getThresholdAmount());
    }

    public Optional<Bank> findBankById(Long id) {
        return null;
    }
}
