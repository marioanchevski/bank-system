package com.mybanksystem.account.model.exceptions;

public class ZeroAmountException extends InsufficientFundsException{
    public ZeroAmountException() {
        super("Can not transfer 0.00$");
    }
}
