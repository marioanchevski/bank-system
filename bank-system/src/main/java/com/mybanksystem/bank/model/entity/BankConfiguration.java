package com.mybanksystem.bank.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
public class BankConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private BigDecimal thresholdAmount;
    private BigDecimal flatFeeAmount;
    private BigInteger percentFeeAmount;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Bank bank;


    public BankConfiguration(BigDecimal thresholdAmount, BigDecimal flatFeeAmount, BigInteger percentFeeAmount, Bank bank) {
        this.thresholdAmount = thresholdAmount;
        this.flatFeeAmount = flatFeeAmount;
        this.percentFeeAmount = percentFeeAmount;
        this.bank = bank;
    }


    public BankConfiguration(BigDecimal thresholdAmount, BigDecimal flatFeeAmount, BigInteger percentFeeAmount) {
        this.thresholdAmount = thresholdAmount;
        this.flatFeeAmount = flatFeeAmount;
        this.percentFeeAmount = percentFeeAmount;
    }

    public BankConfiguration() {
    }

    public BigDecimal getThresholdAmount() {
        return thresholdAmount;
    }

    public void setThresholdAmount(BigDecimal thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    public BigDecimal getFlatFeeAmount() {
        return flatFeeAmount;
    }

    public void setFlatFeeAmount(BigDecimal flatFeeAmount) {
        this.flatFeeAmount = flatFeeAmount;
    }

    public BigInteger getPercentFeeAmount() {
        return percentFeeAmount;
    }

    public void setPercentFeeAmount(BigInteger percentFeeAmount) {
        this.percentFeeAmount = percentFeeAmount;
    }

    public Long getId() {
        return id;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "BankConfiguration{" +
                "id=" + id +
                ", thresholdAmount=" + thresholdAmount +
                ", flatFeeAmount=" + flatFeeAmount +
                ", percentFeeAmount=" + percentFeeAmount +
                ", bank=" + bank +
                '}';
    }
}
