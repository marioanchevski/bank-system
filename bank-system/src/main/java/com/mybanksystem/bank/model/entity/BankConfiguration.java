package com.mybanksystem.bank.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(indexes = {})
public class BankConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    // bigdecimal
    private Double thresholdAmount;
    private Double flatFeeAmount;
    private Integer percentFeeAmount;

    @OneToOne(fetch = FetchType.LAZY)
    private Bank bank;


    public BankConfiguration(Double thresholdAmount, Double flatFeeAmount, Integer percentFeeAmount, Bank bank) {
        this.thresholdAmount = thresholdAmount;
        this.flatFeeAmount = flatFeeAmount;
        this.percentFeeAmount = percentFeeAmount;
        this.bank = bank;
    }

    public BankConfiguration() {
    }

    public Double getThresholdAmount() {
        return thresholdAmount;
    }

    public void setThresholdAmount(Double thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    public Double getFlatFeeAmount() {
        return flatFeeAmount;
    }

    public void setFlatFeeAmount(Double flatFeeAmount) {
        this.flatFeeAmount = flatFeeAmount;
    }

    public Integer getPercentFeeAmount() {
        return percentFeeAmount;
    }

    public void setPercentFeeAmount(Integer percentFeeAmount) {
        this.percentFeeAmount = percentFeeAmount;
    }

    public Long getId() {
        return id;
    }

    public Bank getBank() {
        return bank;
    }
}
