package com.mybanksystem.account.service;

import com.mybanksystem.account.model.dto.AccountDTO;

import java.util.List;

public interface FindAllAccountsService {
    List<AccountDTO> findAllAccounts();
}
