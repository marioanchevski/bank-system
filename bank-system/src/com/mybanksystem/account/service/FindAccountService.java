package com.mybanksystem.account.service;

import com.mybanksystem.account.Account;

import java.util.Optional;

public interface FindAccountService {
    Optional<Account> findAccountById(Long accountId);
}
