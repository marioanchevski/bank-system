package com.mybanksystem.bank.service;

import com.mybanksystem.bank.model.BankDTO;

import java.util.List;

public interface FindAllBanksService{
    List<BankDTO> findAllBanks();
}
