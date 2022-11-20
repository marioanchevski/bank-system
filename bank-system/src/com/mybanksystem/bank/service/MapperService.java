package com.mybanksystem.bank.service;

import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.BankDTO;

import java.util.List;

public interface MapperService {
    BankDTO map(Bank bank);
}
