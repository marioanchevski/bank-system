package com.mybanksystem.bank.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NonExistentBankException extends RuntimeException{
    public NonExistentBankException(Long bankId) {
        super(String.format("The bank with ID:%d does not exist.", bankId));
    }

    public NonExistentBankException(String bankName) {
        super(String.format("%s bank  does not exist.", bankName));
    }
}
