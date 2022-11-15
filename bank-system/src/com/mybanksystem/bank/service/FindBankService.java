package com.mybanksystem.bank.service;

import com.mybanksystem.bank.Bank;

import java.util.Optional;

public interface FindBankService {
    Optional<Bank> findBankById(Long bankId);
}
