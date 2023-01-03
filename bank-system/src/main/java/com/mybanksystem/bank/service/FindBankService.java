package com.mybanksystem.bank.service;

import com.mybanksystem.bank.model.dto.BankDTO;

public interface FindBankService {
    BankDTO findBankById(String bankUUID);
}
