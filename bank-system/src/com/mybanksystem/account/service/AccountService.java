package com.mybanksystem.account.service;

import com.mybanksystem.account.exceptions.NonExistentAccountException;


public interface AccountService {
    void updateAccounts(String transactionId);

}
