package com.mybanksystem.bank.model.dto;

import lombok.Data;

@Data
public class BankConfigurationDTO {
    private Double thresholdAmount;
    private Double flatFeeAmount;
    private Integer percentFeeAmount;

    public BankConfigurationDTO(Double thresholdAmount, Double flatFeeAmount, Integer percentFeeAmount) {
        this.thresholdAmount = thresholdAmount;
        this.flatFeeAmount = flatFeeAmount;
        this.percentFeeAmount = percentFeeAmount;
    }
}
