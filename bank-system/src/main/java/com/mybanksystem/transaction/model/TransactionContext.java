package com.mybanksystem.transaction.model;

import com.mybanksystem.transaction.model.enumeration.TransactionType;

import java.math.BigDecimal;

public class TransactionContext {
    private TransactionType type;
    private Long fromId;
    private Long toId;
    private BigDecimal amount;
    private Long bankId;

    public TransactionContext(TransactionType type, Long fromId, Long toId, BigDecimal amount, Long bankId) {
        this.type = type;
        this.fromId = fromId;
        this.toId = toId;
        this.amount = amount;
        this.bankId = bankId;
    }

    public TransactionType getType() {
        return type;
    }

    public Long getFromId() {
        return fromId;
    }

    public Long getToId() {
        return toId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long getBankId() {
        return bankId;
    }
}
