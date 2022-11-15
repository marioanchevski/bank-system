package com.mybanksystem.bank;

import com.mybanksystem.account.Account;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private Long id;
    private String name;
    private List<Account> accounts;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double thresholdAmount;
    private double flatFeeAmount;
    private int percentFeeAmount;
    public Bank(String name, double flatFee, int percentFee, double threshold) {
        this.id = null;
        this.name = name;
        this.accounts = new ArrayList<>();
        totalTransactionFeeAmount = 0.0;
        totalTransferAmount = 0.0;
        flatFeeAmount = flatFee;
        percentFeeAmount = percentFee;
        thresholdAmount = threshold;
    }

    public void setTotalTransactionFeeAmount(double totalTransactionFeeAmount) {
        this.totalTransactionFeeAmount = totalTransactionFeeAmount;
    }
    public void setTotalTransferAmount(double totalTransferAmount) {
        this.totalTransferAmount = totalTransferAmount;
    }
    public double getThresholdAmount() {
        return thresholdAmount;
    }
    public double getFlatFeeAmount() {
        return flatFeeAmount;
    }

    public int getPercentFeeAmount() {
        return percentFeeAmount;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }
    public String getName() {
        return name;
    }
    public List<Account> getAccounts() {
        return accounts;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}