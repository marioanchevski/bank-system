package com.mybanksystem.transaction.model.entity;

import com.mybanksystem.account.model.Account;
import com.mybanksystem.transaction.model.enumeration.TransactionType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("flatamount")
public class FlatAmountProvisionTransaction extends Transaction {
    @Transient
    BigDecimal provision;
    public FlatAmountProvisionTransaction() {
    }

    public FlatAmountProvisionTransaction(Account accountFrom, Account accountTo, BigDecimal amount, String description, TransactionType type, BigDecimal provision) {
        super(accountFrom, accountTo, amount, description, type);
        this.provision = provision;
    }

    @Override
    public BigDecimal getProvision() {

        return provision;
        //return getBank().getFlatFeeAmount();
    }


}
