package com.mybanksystem.transaction.model.dto;

import com.mybanksystem.transaction.model.enumeration.TransactionType;
import lombok.Data;

@Data
public class TransactionDisplayDTO {
    private String bankUUID;
    private String accountFrom;
    private String accountTo;
    private Double amount;
    private TransactionType transactionType;

}
