package com.mybanksystem.bank.service;

import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.exceptions.NonExistentBankException;

public interface FindBankService {
    Bank findBankById(Long bankId) throws NonExistentBankException;
}
