package com.mybanksystem;

import java.util.Optional;

public class AccountService {

    public Optional<Account> findAccountById(Long id, Bank bank) {
        return bank.getAccounts().stream()
                .filter(account -> account.getId().equals(id))
                .findFirst();
    }
    //fromAccount.getTransactions().add(String.format("--deposited %.2f$ to my account, paid: %.2f$ provision of type %s--", amountToTransfer, provision, transaction.getDescription()));
    public void updateAccount(Account accountFrom, Account accountTo, Transaction transaction) {
        accountFrom.getTransactions().add(String.format("%s: %.2f to account: %d, transaction fee: %.2f %s",
                transaction.getType(),
                transaction.getAmount(),
                accountFrom.getId(),
                transaction.getProvision(),
                transaction.getDescription()));

        if (transaction.getType().equals(TransactionType.DEPOSIT))
            accountFrom.setBalance(accountFrom.getBalance() - (transaction.getAmount()*-1 + transaction.getProvision()));
        else if (transaction.getType().equals(TransactionType.WITHDRAW))
            accountFrom.setBalance(accountFrom.getBalance() - (transaction.getAmount() + transaction.getProvision()));
        else {
            accountFrom.setBalance(accountFrom.getBalance() - transaction.getAmount() + transaction.getProvision());
            accountTo.setBalance(accountTo.getBalance() + transaction.getProvision());
            accountTo.getTransactions().add(String.format("RECEIVED: %.2f from account: %d", transaction.getAmount(), accountFrom.getId()));
        }

    }

}
