package com.mybanksystem.account.service.Impl;

import com.mybanksystem.account.JpaAccountRepository;
import com.mybanksystem.account.model.Account;
import com.mybanksystem.account.model.dto.AccountDTO;
import com.mybanksystem.account.service.FindAllAccountsService;
import com.mybanksystem.bank.repository.JpaBankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindAllAccountsServiceImpl implements FindAllAccountsService {
    private final JpaAccountRepository accountRepository;
    private final JpaBankRepository bankRepository;

    @Override
    public List<AccountDTO> findAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::mapAccountTOAccountDTO)
                .collect(Collectors.toList());
    }

    private AccountDTO mapAccountTOAccountDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setAccountOwner(account.getName());
        dto.setBalance(account.getBalance().doubleValue());

        dto.setBank(account.getBank().getName());
        //dto.setBank(bankRepository.findNameById());
        return dto;
    }
}
