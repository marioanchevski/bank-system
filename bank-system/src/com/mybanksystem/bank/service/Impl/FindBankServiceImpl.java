package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.BankRepository;
import com.mybanksystem.bank.exceptions.NonExistentBankException;
import com.mybanksystem.bank.service.FindBankService;

public class FindBankServiceImpl implements FindBankService {
    private final BankRepository bankRepository;

    public FindBankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public Bank findBankById(Long bankId) throws NonExistentBankException {
        return bankRepository.findBankById(bankId)
                .orElseThrow(() -> new NonExistentBankException(bankId));
    }
}
