package com.mybanksystem.bank.service;

import com.mybanksystem.account.exceptions.InsufficientFundsException;
import com.mybanksystem.bank.exceptions.NonExistentBankException;


public interface BankService {
    void makeTransaction(String transactionId, Long bankId) throws InsufficientFundsException, NonExistentBankException;


}
