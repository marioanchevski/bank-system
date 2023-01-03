package com.mybanksystem.account.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NonExistentAccountException extends RuntimeException {
    public NonExistentAccountException(long accountId) {
        super(String.format("The account with ID:%d does not exist.", accountId));
    }
    public NonExistentAccountException(String accountUUID) {
        super(String.format("The account with ID:%s does not exist.", accountUUID));
    }

}
