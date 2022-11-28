package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.BankDTO;
import com.mybanksystem.bank.BankRepository;
import com.mybanksystem.bank.service.FindAllBanksService;
import com.mybanksystem.bank.service.MapperService;

import java.util.List;
import java.util.stream.Collectors;

public class FidAllBanksServiceImpl implements FindAllBanksService {
    private final BankRepository bankRepository;
    private final MapperService mapperService;

    public FidAllBanksServiceImpl(BankRepository bankRepository, MapperService mapperService) {
        this.bankRepository = bankRepository;
        this.mapperService = mapperService;
    }

    @Override
    public List<BankDTO> findAllBanks() {
        List<Bank> banks = bankRepository.findAll();
        List<BankDTO> bankDTOs = banks.stream()
                .map(bank -> mapperService.map(bank))
                .collect(Collectors.toList());

        return bankDTOs;
    }
}
