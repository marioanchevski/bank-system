package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.account.Account;
import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.BankRepository;
import com.mybanksystem.bank.exceptions.NonExistentBankException;
import com.mybanksystem.bank.service.FindAllAccountsInBankService;

import java.util.List;

public class FindAllAccountsInBankServiceImpl implements FindAllAccountsInBankService {
    private final BankRepository bankRepository;

    public FindAllAccountsInBankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public List<Account> findAll(Long bankId) throws NonExistentBankException {
        Bank bank = bankRepository.findBankById(bankId)
                .orElseThrow(() -> new NonExistentBankException(bankId));
        return bank.getAccounts();
    }
}
