package com.mybanksystem.account.service;

import java.math.BigDecimal;

public interface CreateAccountService {
    String addAccountToBank(String name, BigDecimal balance, String bankName);
}
