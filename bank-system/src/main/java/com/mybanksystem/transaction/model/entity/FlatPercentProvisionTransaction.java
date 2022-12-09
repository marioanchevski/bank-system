package com.mybanksystem.transaction.model.entity;

import com.mybanksystem.account.model.Account;
import com.mybanksystem.transaction.model.enumeration.TransactionType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("percent")
public class FlatPercentProvisionTransaction extends Transaction {
    @Transient
    double provision;
    public FlatPercentProvisionTransaction() {
    }

    public FlatPercentProvisionTransaction(Account accountFrom, Account accountTo, Double amount, String description, TransactionType type, Double provision) {
        super(accountFrom, accountTo, amount, description, type);
        this.provision = provision;
    }

    @Override
    public double getProvision() {
        return provision;
    }
}
