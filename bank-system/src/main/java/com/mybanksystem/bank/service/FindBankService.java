package com.mybanksystem.bank.service;

import com.mybanksystem.bank.model.entity.Bank;
import com.mybanksystem.bank.model.exceptions.NonExistentBankException;


public interface FindBankService {
    Bank findBankById(Long bankId) throws NonExistentBankException;
}
