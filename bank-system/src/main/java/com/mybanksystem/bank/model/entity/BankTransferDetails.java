package com.mybanksystem.bank.model.entity;

import javax.persistence.*;

@Entity
public class BankTransferDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Double totalTransactionFeeAmount;
    private Double totalTransferAmount;

    @OneToOne(fetch = FetchType.LAZY)
    private Bank bank;

    public BankTransferDetails(Bank bank) {
        this.totalTransactionFeeAmount = 0.0;
        this.totalTransferAmount = 0.0;
        this.bank = bank;
    }

    public BankTransferDetails() {
    }


    public Double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public void setTotalTransactionFeeAmount(Double totalTransactionFeeAmount) {
        this.totalTransactionFeeAmount = totalTransactionFeeAmount;
    }

    public Double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public void setTotalTransferAmount(Double totalTransferAmount) {
        this.totalTransferAmount = totalTransferAmount;
    }
}
