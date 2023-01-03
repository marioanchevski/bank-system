package com.mybanksystem.account.service.Impl;

import com.mybanksystem.account.model.Account;
import com.mybanksystem.account.JpaAccountRepository;
import com.mybanksystem.account.service.CreateAccountService;
import com.mybanksystem.bank.repository.JpaBankRepository;
import com.mybanksystem.bank.model.exceptions.NonExistentBankException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateAccountServiceImpl implements CreateAccountService {
    private final JpaBankRepository bankRepository;
    private final JpaAccountRepository accountRepository;


    @Override
    public String addAccountToBank(String name, BigDecimal balance, String bankName) {
        var bank = bankRepository.findBankByName(bankName)
                .orElseThrow(() -> new NonExistentBankException(bankName));

        var account = new Account(name, balance);
        var uuid = UUID.randomUUID().toString();
        account.setBank(bank);
        account.setUUID(uuid);
        accountRepository.save(account);

        return uuid;
    }
}
