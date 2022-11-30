package com.mybanksystem.account;

import com.mybanksystem.util.Bean;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AccountRepository implements Bean {
    private static Long idSeed = 10000L;
    private Map<Long, Account> accountData;

    public AccountRepository() {
        accountData = new HashMap<>();
    }

    public void saveAccount(Account account) {
        Long accountId = generateAccountId();
        account.setId(accountId);
        accountData.put(accountId, account);
    }

    public Optional<Account> findAccountById(Long accountId) {
        return Optional.ofNullable(accountData.get(accountId));
    }

    private Long generateAccountId() {
        return idSeed++;
    }

}
