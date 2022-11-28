package com.mybanksystem.account.exceptions;

public class NonExistentAccountException extends Exception {
    public NonExistentAccountException(long accountId) {
        super(String.format("The account with ID:%d does not exist.", accountId));
    }
}
