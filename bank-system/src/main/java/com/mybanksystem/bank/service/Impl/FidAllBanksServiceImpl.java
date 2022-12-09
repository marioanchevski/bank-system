package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.bank.model.entity.Bank;
import com.mybanksystem.bank.model.BankDTO;
import com.mybanksystem.bank.repository.JpaBankRepository;
import com.mybanksystem.bank.service.FindAllBanksService;
import com.mybanksystem.bank.service.MapperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FidAllBanksServiceImpl implements FindAllBanksService {
    private final JpaBankRepository bankRepository;
    private final MapperService mapperService;

    public FidAllBanksServiceImpl(JpaBankRepository bankRepository, MapperService mapperService) {
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
