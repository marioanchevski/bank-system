package com.mybanksystem.account.service;

import com.mybanksystem.util.Bean;

public interface AccountService extends Bean {
    void updateAccounts(String transactionId);

}
