package com.mybanksystem.account.service;

import com.mybanksystem.account.model.exceptions.NonExistentAccountException;

public interface AccountPrintingService {

    void printAccountDetails(Long accountId) throws NonExistentAccountException;

}
