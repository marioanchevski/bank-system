package com.mybanksystem.bank.service;

import com.mybanksystem.account.Account;
import com.mybanksystem.bank.exceptions.NonExistentBankException;

import java.util.List;

public interface FindAllAccountsInBankService {
    List<Account> findAll(Long bankId) throws NonExistentBankException;
}
