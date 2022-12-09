package com.mybanksystem.bank.service;

import com.mybanksystem.bank.model.entity.Bank;
import com.mybanksystem.bank.model.BankDTO;

public interface MapperService {
    BankDTO map(Bank bank);
}
