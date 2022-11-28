package com.mybanksystem.account.service;

import com.mybanksystem.util.Bean;
import com.mybanksystem.account.Account;

import java.util.Optional;

public interface FindAccountService extends Bean {
    Optional<Account> findAccountById(Long accountId);
}
