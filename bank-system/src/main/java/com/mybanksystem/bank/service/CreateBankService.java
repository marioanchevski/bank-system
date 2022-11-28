package com.mybanksystem.bank.service;

import com.mybanksystem.util.Bean;

public interface CreateBankService extends Bean {
    void createNewBank(String name, Double thresholdAmount, Double flatFeeAmount, Integer percentFeeAmount);
}
