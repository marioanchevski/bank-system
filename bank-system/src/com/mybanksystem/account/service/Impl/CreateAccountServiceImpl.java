package com.mybanksystem.account.service.Impl;

import com.mybanksystem.account.Account;
import com.mybanksystem.account.AccountRepository;
import com.mybanksystem.account.service.CreateAccountService;
import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.exceptions.NonExistentBankException;
import com.mybanksystem.bank.service.FindBankService;

public class CreateAccountServiceImpl implements CreateAccountService {
    private final FindBankService findBankService;
    private final AccountRepository accountRepository;

    public CreateAccountServiceImpl(FindBankService findBankService, AccountRepository accountRepository) {
        this.findBankService = findBankService;
        this.accountRepository = accountRepository;
    }

    @Override
    public void addAccountToBank(String name, Double balance, Long bankId) throws NonExistentBankException {
        Bank bank = findBankService.findBankById(bankId);
        Account account = new Account(name, balance);
        accountRepository.saveAccount(account);
        bank.getAccounts().add(account);
    }
}
