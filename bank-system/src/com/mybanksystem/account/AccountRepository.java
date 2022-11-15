package com.mybanksystem.account;

import java.util.*;

public class AccountRepository {
    private static Long idSeed = 10000L;
    private Map<Long, Account> accountData;

    public AccountRepository() {
        Account a1 = new Account("Mario", 5.00);
        Account a2 = new Account("Vojo", 1000.00);
        Account a3 = new Account("Filip and Ata", 5000.50);
        accountData = new HashMap<>();
        saveAccount(a1);
        saveAccount(a2);
        saveAccount(a3);
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

    public List<Account> getAllAccount() {
        return new ArrayList<>(accountData.values());
    }
}
