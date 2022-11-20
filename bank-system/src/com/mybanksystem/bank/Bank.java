package com.mybanksystem.bank;

import com.mybanksystem.account.Account;
import com.mybanksystem.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private Long id;
    private String name;
    private List<Account> accounts;
    private Double totalTransactionFeeAmount;
    private Double totalTransferAmount;
    private Double thresholdAmount;
    private Double flatFeeAmount;
    private Integer percentFeeAmount;
    private List<Transaction> bankTransactions;
    public Bank(String name, double flatFee, int percentFee, double threshold) {
        this.id = null;
        this.name = name;
        this.accounts = new ArrayList<>();
        this.bankTransactions =  new ArrayList<>();
        this.totalTransactionFeeAmount = 0.0;
        this.totalTransferAmount = 0.0;
        this.flatFeeAmount = flatFee;
        this.percentFeeAmount = percentFee;
        this.thresholdAmount = threshold;
    }

    public void setTotalTransactionFeeAmount(double totalTransactionFeeAmount) {
        this.totalTransactionFeeAmount = totalTransactionFeeAmount;
    }
    public void setTotalTransferAmount(double totalTransferAmount) {
        this.totalTransferAmount = totalTransferAmount;
    }
    public Double getThresholdAmount() {
        return thresholdAmount;
    }
    public Double getFlatFeeAmount() {
        return flatFeeAmount;
    }

    public Integer getPercentFeeAmount() {
        return percentFeeAmount;
    }

    public Double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public Double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }
    public String getName() {
        return name;
    }
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Transaction> getBankTransactions() {
        return bankTransactions;
    }

    public Long getId() {
        return id;
    }
}