package com.mybanksystem.bank.exceptions;

public class NonExistentBankException extends Exception{
    public NonExistentBankException(Long bankId) {
        super(String.format("The bank with ID:%d does not exist.", bankId));
    }
}
