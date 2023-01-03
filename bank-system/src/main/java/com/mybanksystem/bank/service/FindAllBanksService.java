package com.mybanksystem.bank.service;

import com.mybanksystem.bank.model.dto.BankDTO;

import java.util.List;

public interface FindAllBanksService{
    List<BankDTO> findAllBanks();
}
