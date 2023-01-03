package com.mybanksystem.controller;

import com.mybanksystem.transaction.model.dto.TransactionDTO;
import com.mybanksystem.transaction.model.enumeration.TransactionType;
import com.mybanksystem.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/withdraw")
    public ResponseEntity<String> makeWithdraw(@RequestBody @Valid TransactionDTO transactionDTO) {
        transactionDTO.setAccountToUUID(transactionDTO.getAccountFromUUID());
        return new ResponseEntity<>(
                transactionService.createTransaction(transactionDTO, TransactionType.WITHDRAW),
                HttpStatus.CREATED);
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> makeDeposit(@RequestBody @Valid TransactionDTO transactionDTO) {
        transactionDTO.setAccountToUUID(transactionDTO.getAccountFromUUID());
        return new ResponseEntity<>(
                transactionService.createTransaction(transactionDTO, TransactionType.DEPOSIT),
                HttpStatus.CREATED);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> sendFunds(@RequestBody @Valid TransactionDTO transactionDTO) {
        return new ResponseEntity<>(
                transactionService.createTransaction(transactionDTO, TransactionType.TRANSFER),
                HttpStatus.CREATED);
    }

}
