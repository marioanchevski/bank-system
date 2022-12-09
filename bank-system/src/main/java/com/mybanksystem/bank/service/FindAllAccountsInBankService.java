package com.mybanksystem.bank.service;

import com.mybanksystem.account.model.Account;
import com.mybanksystem.bank.model.exceptions.NonExistentBankException;

import java.util.List;

public interface FindAllAccountsInBankService{
    List<Account> findAll(Long bankId) throws NonExistentBankException;
}
