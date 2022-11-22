package com.mybanksystem.bank.service;

import com.mybanksystem.util.Bean;
import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.BankDTO;

public interface MapperService extends Bean {
    BankDTO map(Bank bank);
}
