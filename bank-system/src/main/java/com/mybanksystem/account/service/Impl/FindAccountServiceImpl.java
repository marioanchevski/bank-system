package com.mybanksystem.account.service.Impl;

import com.mybanksystem.account.model.Account;
import com.mybanksystem.account.JpaAccountRepository;
import com.mybanksystem.account.model.dto.AccountDTO;
import com.mybanksystem.account.model.exceptions.NonExistentAccountException;
import com.mybanksystem.account.service.FindAccountService;
import com.mybanksystem.bank.repository.JpaBankRepository;
import com.mybanksystem.transaction.JpaTransactionRepository;
import com.mybanksystem.transaction.model.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindAccountServiceImpl implements FindAccountService {

    private final JpaAccountRepository accountRepository;
    private final JpaTransactionRepository transactionRepository;

    @Override
    public Optional<Account> findAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public AccountDTO findAccountById(String accountUUID) {
        // made changes here
         Account account = accountRepository.findAccountByUUID(accountUUID)
                .orElseThrow(() -> new NonExistentAccountException(accountUUID));

        return mapAccountToAccountDTO(account);
    }

    private AccountDTO mapAccountToAccountDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setAccountOwner(account.getName());
        dto.setBalance(account.getBalance().doubleValue());
        dto.setBank(account.getBank().getName());
        return dto;
    }
}
