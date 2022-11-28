package com.mybanksystem.bank;

import com.mybanksystem.account.Account;
import com.mybanksystem.transaction.Transaction;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
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
}