package com.mybanksystem.bank.service;

import com.mybanksystem.account.model.dto.AccountDTO;

import java.util.Collection;

public interface FindAllAccountsInBankService {
    Collection<AccountDTO> findAll(String bankUUID);
}
