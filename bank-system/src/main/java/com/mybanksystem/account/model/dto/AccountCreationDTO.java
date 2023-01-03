package com.mybanksystem.account.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class AccountCreationDTO {
    @NotBlank(message = "Please provide your name.")
    private String accountOwner;
    @NotNull(message = "Please enter you balance.")
    @Positive(message = "You can not open an account with negative balance.")
    private Double balance;
    @NotBlank(message = "Please enter the bank in which you want to open an account.")
    private String bank;
}
