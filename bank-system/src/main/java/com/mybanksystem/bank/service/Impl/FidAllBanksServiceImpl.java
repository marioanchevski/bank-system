package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.bank.model.entity.Bank;
import com.mybanksystem.bank.model.dto.BankDTO;
import com.mybanksystem.bank.repository.JpaBankRepository;
import com.mybanksystem.bank.service.FindAllBanksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FidAllBanksServiceImpl implements FindAllBanksService {
    private final JpaBankRepository bankRepository;

    @Override
    public List<BankDTO> findAllBanks() {
        return bankRepository.findAll().stream()
                .map(mapBankToBankDTO())
                .collect(Collectors.toList());
    }

    private Function<Bank, BankDTO> mapBankToBankDTO() {
        return bank -> {
            var dto = new BankDTO();
            dto.setBankName(bank.getName());
            dto.setUUID(bank.getUUID());
            return dto;
        };
    }

}
