package com.mybanksystem.bank.service;

import com.mybanksystem.util.Bean;
import com.mybanksystem.account.exceptions.InsufficientFundsException;
import com.mybanksystem.bank.exceptions.NonExistentBankException;


public interface BankService extends Bean {
    void makeTransaction(String transactionId, Long bankId) throws InsufficientFundsException, NonExistentBankException;


}
