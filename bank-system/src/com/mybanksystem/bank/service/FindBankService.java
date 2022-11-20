package com.mybanksystem.bank.service;

import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.exceptions.NonExistentBankException;

import java.util.Optional;

public interface FindBankService {
    Optional<Bank> findBankById(Long bankId);
}
