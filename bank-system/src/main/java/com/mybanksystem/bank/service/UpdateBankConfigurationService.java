package com.mybanksystem.bank.service;

import com.mybanksystem.bank.model.dto.BankConfigurationDTO;

public interface UpdateBankConfigurationService {
    BankConfigurationDTO updateBankConfigurationForBank(String bankUUID, Double thresholdAmount, Double flatFeeAmount, Integer percentFeeAmount);
}
