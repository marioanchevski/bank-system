package com.mybanksystem.bank.service;

import com.mybanksystem.bank.model.exceptions.NonExistentBankException;

public interface BankPrintingService {
    String printBankDetails(Long bankId) throws NonExistentBankException;
}
