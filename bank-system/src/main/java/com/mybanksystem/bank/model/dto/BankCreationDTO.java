package com.mybanksystem.bank.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BankCreationDTO {
    @NotBlank(message = "Please provide a name for your bank.")
    private String bankName;
}
