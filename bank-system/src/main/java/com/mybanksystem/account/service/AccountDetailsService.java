package com.mybanksystem.account.service;

import com.mybanksystem.account.model.dto.DetailedAccountDTO;

public interface AccountDetailsService {
    DetailedAccountDTO getAccountDetails(String accountUUID);
}
