package com.mybanksystem.bank;

import com.mybanksystem.account.Account;
import com.mybanksystem.account.exceptions.InsufficientFundsException;
import com.mybanksystem.account.exceptions.NonExistentAccountException;
import com.mybanksystem.transaction.Transaction;

public interface BankService {
    void makeTransaction(Transaction transaction, Bank bank) throws InsufficientFundsException, NonExistentAccountException;
    void addAccountToBank(Account account, Bank bank);
    String getTotalTransactionFeeAmount(Bank bank);
    String getTotalTotalTransferAmount(Bank bank);
    String printBank(Bank bank);
    boolean hasAccounts(Bank bank);
}
