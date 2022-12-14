package com.mybanksystem.bank.model.entity;


import javax.persistence.*;


@Entity
public class Bank {
    @Id
    @SequenceGenerator(
            name = "bank_seq",
            sequenceName = "BANK_SEQ",
            initialValue = 100,
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "bank_seq",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String name;

    @OneToOne(mappedBy = "bank", cascade = CascadeType.ALL)

    private BankTransferDetails bankTransferDetails;

    @OneToOne(mappedBy = "bank", cascade = CascadeType.ALL)
    private BankConfiguration bankConfiguration;

    public Bank() {
    }


    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public BankConfiguration getBankConfiguration() {
        return bankConfiguration;
    }

    public BankTransferDetails getBankTransferDetails() {
        return bankTransferDetails;
    }
}