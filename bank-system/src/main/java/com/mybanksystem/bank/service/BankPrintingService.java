package com.mybanksystem.bank.service;

import com.mybanksystem.util.Bean;
import com.mybanksystem.bank.exceptions.NonExistentBankException;

public interface BankPrintingService extends Bean {
    String printBankDetails(Long bankId) throws NonExistentBankException;
}
