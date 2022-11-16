package com.mybanksystem.account.service;

import com.mybanksystem.account.exceptions.NonExistentAccountException;
import com.mybanksystem.bank.exceptions.NonExistentBankException;

public interface AccountPrintingService {

    void printAccountDetails(Long accountId) throws NonExistentAccountException;
    void printAllAccountsInBank(Long bankId) throws NonExistentBankException;
}
