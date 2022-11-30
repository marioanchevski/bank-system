package com.mybanksystem.account.service;

import com.mybanksystem.bank.exceptions.NonExistentBankException;

public interface CreateAccountService {
    void addAccountToBank(String name, Double balance, Long bankId) throws NonExistentBankException;
}
