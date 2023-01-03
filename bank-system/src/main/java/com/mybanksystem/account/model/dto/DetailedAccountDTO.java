package com.mybanksystem.account.model.dto;

import com.mybanksystem.transaction.model.dto.TransactionDisplayDTO;
import lombok.Data;

import java.util.List;

@Data
public class DetailedAccountDTO {
    private String accountOwner;
    private String bank;
    private Double balance;
    private List<TransactionDisplayDTO> transactions;
}
