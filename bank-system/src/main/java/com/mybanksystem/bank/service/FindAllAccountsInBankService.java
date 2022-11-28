package com.mybanksystem.bank.service;

import com.mybanksystem.util.Bean;
import com.mybanksystem.account.Account;
import com.mybanksystem.bank.exceptions.NonExistentBankException;

import java.util.List;

public interface FindAllAccountsInBankService extends Bean {
    List<Account> findAll(Long bankId) throws NonExistentBankException;
}
