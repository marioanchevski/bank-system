package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.BankRepository;
import com.mybanksystem.bank.service.CreateBankService;

public class CreateBankServiceImpl implements CreateBankService {
    private final BankRepository bankRepository;

    public CreateBankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public void createNewBank(String name, Double thresholdAmount, Double flatFeeAmount, Integer percentFeeAmount) {
        Bank bank = new Bank(name, flatFeeAmount, percentFeeAmount, thresholdAmount);
        bankRepository.saveBank(bank);
    }
}
