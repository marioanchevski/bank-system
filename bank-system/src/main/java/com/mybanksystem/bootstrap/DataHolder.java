package com.mybanksystem.bootstrap;


import com.mybanksystem.account.Account;
import com.mybanksystem.account.AccountRepository;
import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.BankRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

// temporary class
// in memory database to store the Banks
@Component
public class DataHolder {

    private final BankRepository bankRepository;
    private final AccountRepository accountRepository;
    public DataHolder(BankRepository bankRepository, AccountRepository accountRepository) {
        this.bankRepository = bankRepository;
        this.accountRepository = accountRepository;
    }
    @PostConstruct
    public void init() {
        Account a1 = new Account("Mario", 5.00);
        Account a2 = new Account("Vojo", 1000.00);
        Account a3 = new Account("Filip and Ata", 5000.50);
        accountRepository.saveAccount(a1);
        accountRepository.saveAccount(a2);
        accountRepository.saveAccount(a3);
        Bank testBank = new Bank("LinkPlus-Bank", 10.00, 2, 10000.00);
        Bank secondBank = new Bank("NLB", 25.00, 10, 5000.55);
        bankRepository.saveBank(testBank);
        bankRepository.saveBank(secondBank);
        bankRepository.findBankById(100L).get().setAccounts(new ArrayList<>(List.of(a1, a2, a3)));
    }

}
