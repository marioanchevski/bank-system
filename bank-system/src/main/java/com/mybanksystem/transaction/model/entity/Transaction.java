package com.mybanksystem.transaction.model.entity;

import com.mybanksystem.account.model.Account;
import com.mybanksystem.transaction.model.enumeration.TransactionType;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "provision_type")
public abstract class Transaction {
    @SequenceGenerator(
            name = "transaction_seq",
            sequenceName = "TRANSACTION_SEQ"
    )
    @Id
    @GeneratedValue(
            generator = "transaction_seq",
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @ManyToOne
    private Account accountFrom;
    @ManyToOne
    private Account accountTo;
    private Double amount;
    private String description;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public Transaction() {
    }

    public Transaction(Account accountFrom, Account accountTo, Double amount, String description, TransactionType type) {
        this.id = null;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.description = description;
        this.type = type;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public abstract double getProvision();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
