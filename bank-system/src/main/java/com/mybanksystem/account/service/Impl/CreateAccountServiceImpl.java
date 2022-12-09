package com.mybanksystem.account.service.Impl;

import com.mybanksystem.account.model.Account;
import com.mybanksystem.account.JpaAccountRepository;
import com.mybanksystem.account.service.CreateAccountService;
import com.mybanksystem.bank.model.entity.Bank;
import com.mybanksystem.bank.repository.JpaBankRepository;
import com.mybanksystem.bank.model.exceptions.NonExistentBankException;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountServiceImpl implements CreateAccountService {
    private final JpaBankRepository bankRepository;
    private final JpaAccountRepository accountRepository;

    public CreateAccountServiceImpl(JpaBankRepository bankRepository, JpaAccountRepository accountRepository) {
        this.bankRepository = bankRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void addAccountToBank(String name, Double balance, Long bankId) throws NonExistentBankException {
        Bank bank = bankRepository.findById(bankId).get();
        Account account = new Account(name, balance, bank);
        accountRepository.save(account);

    }
}
