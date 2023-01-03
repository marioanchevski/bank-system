package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.bank.model.entity.Bank;
import com.mybanksystem.bank.model.dto.BankDTO;
import com.mybanksystem.bank.service.MapperService;
import org.springframework.stereotype.Service;

@Service
public class MapperServiceImpl implements MapperService {
    @Override
    public BankDTO map(Bank bank) {
        BankDTO bankDTO = new BankDTO();
        //bankDTO.setId(bank.getId());
        bankDTO.setBankName(bank.getName());
        return bankDTO;
    }
}
