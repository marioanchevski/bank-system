package com.mybanksystem.transaction.model.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class TransactionDTO {
    @NotBlank(message = "Please provide the account.")
    private String accountFromUUID;
    private String accountToUUID;
    @Min(value = 1, message = "You can not transfer 0.00$.")
    private Double amount;
}
