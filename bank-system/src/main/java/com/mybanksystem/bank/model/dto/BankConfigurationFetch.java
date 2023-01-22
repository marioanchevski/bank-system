package com.mybanksystem.bank.model.dto;

import com.mybanksystem.bank.model.BankConfigurationGroupType;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class BankConfigurationFetch {
    private Long id;
    private BigDecimal thresholdAmount;
    private BigDecimal flatFeeAmount;
    private BigInteger percentFeeAmount;
    private BankConfigurationGroupType groupation;
}
