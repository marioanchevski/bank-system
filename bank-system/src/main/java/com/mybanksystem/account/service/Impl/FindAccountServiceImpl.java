package com.mybanksystem.account.service.Impl;

import com.mybanksystem.account.model.Account;
import com.mybanksystem.account.JpaAccountRepository;
import com.mybanksystem.account.service.FindAccountService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class FindAccountServiceImpl implements FindAccountService {

    private final JpaAccountRepository accountRepository;

    public FindAccountServiceImpl(JpaAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> findAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }
}
