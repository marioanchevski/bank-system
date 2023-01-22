package com.mybanksystem.bank.model.entity;


import lombok.*;


import javax.persistence.*;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
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
    private String UUID;

    private String name;

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
    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return id != null && Objects.equals(id, bank.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}