package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.account.model.Account;
import com.mybanksystem.account.JpaAccountRepository;
import com.mybanksystem.bank.model.exceptions.NonExistentBankException;
import com.mybanksystem.bank.service.FindAllAccountsInBankService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllAccountsInBankServiceImpl implements FindAllAccountsInBankService {
    private final JpaAccountRepository accountRepository;

    public FindAllAccountsInBankServiceImpl(JpaAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll(Long bankId) throws NonExistentBankException {
        return accountRepository.findAll().stream().filter(i->i.getBank().getId().equals(bankId))
                .collect(Collectors.toList());
    }
}
