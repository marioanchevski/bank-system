package com.mybanksystem.bank.service;

public interface CreateBankService {
    void createNewBank(String name, Double thresholdAmount, Double flatFeeAmount, Integer percentFeeAmount);
}
