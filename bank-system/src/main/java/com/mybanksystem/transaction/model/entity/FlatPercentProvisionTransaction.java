package com.mybanksystem.transaction.model.entity;

import com.mybanksystem.account.model.Account;
import com.mybanksystem.transaction.model.enumeration.TransactionType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@DiscriminatorValue("percent")
public class FlatPercentProvisionTransaction extends Transaction {
    @Transient
    BigInteger provision;
    public FlatPercentProvisionTransaction() {
    }

    public FlatPercentProvisionTransaction(Account accountFrom, Account accountTo, BigDecimal amount, String description, TransactionType type, BigInteger provision) {
        super(accountFrom, accountTo, amount, description, type);
        this.provision = provision;
    }

    @Override
    public BigDecimal getProvision() {
        //context.getAmount() * (bankConfiguration.getPercentFeeAmount().doubleValue() / 100
       return super.getAmount().multiply(BigDecimal.valueOf(provision.intValue()).divide(BigDecimal.valueOf(100)));
    }
}
