package com.mybanksystem.account.service.Impl;

import com.mybanksystem.account.Account;
import com.mybanksystem.account.AccountRepository;
import com.mybanksystem.account.service.FindAccountService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class FindAccountServiceImpl implements FindAccountService {

    private final AccountRepository accountRepository;

    public FindAccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> findAccountById(Long accountId) {
        return accountRepository.findAccountById(accountId);
    }
}
