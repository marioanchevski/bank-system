package com.mybanksystem.account;

import java.util.*;

public class AccountRepository {
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
        if (accountData.get(accountId) == null)
            return Optional.empty();
        else
            return Optional.of(accountData.get(accountId));
    }

    private Long generateAccountId() {
        return idSeed++;
    }

}
