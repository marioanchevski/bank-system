package com.mybanksystem.account.service;

import com.mybanksystem.account.model.Account;
import com.mybanksystem.account.model.dto.AccountDTO;

import java.util.Optional;

public interface FindAccountService {
    Optional<Account> findAccountById(Long accountId);
    AccountDTO findAccountById(String accountUUID);
}
