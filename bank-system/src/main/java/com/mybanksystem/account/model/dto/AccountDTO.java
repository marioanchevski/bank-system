package com.mybanksystem.account.model.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private String accountOwner;
    private String bank;
    private Double balance;

    public AccountDTO() {
    }


}
