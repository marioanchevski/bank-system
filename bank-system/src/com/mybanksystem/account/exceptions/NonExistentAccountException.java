package com.mybanksystem.account.exceptions;

public class NonExistentAccountException extends Exception {
    public NonExistentAccountException(long accountId, String bankName) {
        super(String.format("The account with ID:%d does not exist in %s.", accountId, bankName));
    }
}
