package com.mybanksystem.account.service;

import com.mybanksystem.util.Bean;
import com.mybanksystem.bank.exceptions.NonExistentBankException;

public interface CreateAccountService extends Bean {
    void addAccountToBank(String name, Double balance, Long bankId) throws NonExistentBankException;
}
