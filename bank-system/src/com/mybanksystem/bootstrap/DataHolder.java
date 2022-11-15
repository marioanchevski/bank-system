package com.mybanksystem.bootstrap;


import com.mybanksystem.account.Account;
import com.mybanksystem.account.AccountRepository;
import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.BankRepository;

import java.util.ArrayList;
import java.util.List;

// temporary class
// in memory database to store the Banks
public class DataHolder {

    private final BankRepository bankRepository;
    private final AccountRepository accountRepository;

    public static List<Bank> banks = new ArrayList<>();

    public DataHolder(BankRepository bankRepository, AccountRepository accountRepository) {
        this.bankRepository = bankRepository;
        this.accountRepository = accountRepository;
    }

    public void init() {
        bankRepository.findBankById(100L).get().setAccounts(accountRepository.getAllAccount());
/*        Bank bank = new Bank("LinkPlus-Bank", 10.00, 2, 10000.00);
        bank.getAccounts().add(new Account("Mario", 5.00));
        bank.getAccounts().add(new Account("Vojo", 1000.00));
        bank.getAccounts().add(new Account("Filip and Ata", 5000.50));
        banks.add(bank);*/
    }

}
