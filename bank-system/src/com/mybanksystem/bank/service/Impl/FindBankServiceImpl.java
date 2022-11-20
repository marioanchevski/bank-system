package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.BankRepository;
import com.mybanksystem.bank.exceptions.NonExistentBankException;
import com.mybanksystem.bank.service.FindBankService;

import java.util.Optional;

public class FindBankServiceImpl implements FindBankService {
    private final BankRepository bankRepository;

    public FindBankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }
    @Override
    public Optional<Bank> findBankById(Long bankId) {
        return bankRepository.findBankById(bankId);
    }
}
