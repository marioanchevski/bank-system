package com.mybanksystem.bank.service;

import com.mybanksystem.account.model.exceptions.InsufficientFundsException;
import com.mybanksystem.bank.model.exceptions.NonExistentBankException;


public interface BankService {
    void makeTransaction(Long transactionId, Long bankId) throws InsufficientFundsException, NonExistentBankException;


}
