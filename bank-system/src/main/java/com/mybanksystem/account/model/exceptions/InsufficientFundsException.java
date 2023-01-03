package com.mybanksystem.account.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(double amount) {
        super(String.format("You can not deposit %.2f$. After the bank fee's you would have negative balance in your account.", amount));
    }
    public InsufficientFundsException(double balance, double amount) {
        super(String.format("Your current balance is: %.2f%nCan not withdraw %.2f$", balance, amount));
    }
    public InsufficientFundsException(String message) {
        super(message);
    }

}
