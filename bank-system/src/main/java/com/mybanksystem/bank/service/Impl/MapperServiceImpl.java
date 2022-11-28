package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.BankDTO;
import com.mybanksystem.bank.service.MapperService;

public class MapperServiceImpl implements MapperService {
    @Override
    public BankDTO map(Bank bank) {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setId(bank.getId());
        bankDTO.setName(bank.getName());
        return bankDTO;
    }
}