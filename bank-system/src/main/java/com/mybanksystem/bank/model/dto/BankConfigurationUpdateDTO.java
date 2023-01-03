package com.mybanksystem.bank.model.dto;

import lombok.Data;

@Data
public class BankConfigurationUpdateDTO {
    private Double thresholdAmount;
    private Double flatFeeAmount;
    private Integer percentFeeAmount;
}
