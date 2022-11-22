package com.mybanksystem.bank.service;

import com.mybanksystem.util.Bean;
import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.exceptions.NonExistentBankException;

public interface FindBankService extends Bean {
    Bank findBankById(Long bankId) throws NonExistentBankException;
}
