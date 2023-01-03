package com.mybanksystem.bank.service;

import com.mybanksystem.bank.model.BankConfigurationGroupType;
import com.mybanksystem.bank.model.dto.BankDTO;

public interface SetConfigurationForBankService {
    BankDTO createAndSetBankConfiguration(String bankUUID, BankConfigurationGroupType group);
}
