package com.mybanksystem.bank.model.dto;

import lombok.Data;

@Data
public class BankDTO {
    private String bankName;
    private String UUID;

    // config
    public BankDTO() {
    }
    public BankDTO(String name) {
        this.bankName = name;
    }

    public BankDTO(String bankName, String UUID) {
        this.bankName = bankName;
        this.UUID = UUID;
    }
}
