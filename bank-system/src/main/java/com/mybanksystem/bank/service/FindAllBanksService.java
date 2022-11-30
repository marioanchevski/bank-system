package com.mybanksystem.bank.service;

import com.mybanksystem.util.Bean;
import com.mybanksystem.bank.BankDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FindAllBanksService extends Bean {
    List<BankDTO> findAllBanks();
}
