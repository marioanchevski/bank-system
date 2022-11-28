package com.mybanksystem.account.service;

import com.mybanksystem.util.Bean;
import com.mybanksystem.account.exceptions.NonExistentAccountException;

public interface AccountPrintingService extends Bean {

    void printAccountDetails(Long accountId) throws NonExistentAccountException;

}
