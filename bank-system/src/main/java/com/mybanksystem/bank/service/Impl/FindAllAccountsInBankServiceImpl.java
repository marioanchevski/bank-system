package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.account.model.Account;
import com.mybanksystem.account.JpaAccountRepository;
import com.mybanksystem.account.model.dto.AccountDTO;
import com.mybanksystem.bank.service.FindAllAccountsInBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindAllAccountsInBankServiceImpl implements FindAllAccountsInBankService {
    private final JpaAccountRepository accountRepository;

    @Override
    public Collection<AccountDTO> findAll(String bankUUID) {

        return accountRepository.findAllByBankUUID(bankUUID)
                .stream()
                .map(mapAccountToAccountDTO())
                .collect(Collectors.toSet());
    }

    private Function<Account, AccountDTO> mapAccountToAccountDTO() {
        return account -> {
            var dto = new AccountDTO();
            dto.setAccountOwner(account.getName());
            dto.setBalance(account.getBalance().doubleValue());
            dto.setBank(account.getBank().getName());
            return dto;
        };
    }
}
