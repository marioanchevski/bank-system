package com.mybanksystem.bootstrap;


import com.mybanksystem.account.model.Account;
import com.mybanksystem.account.JpaAccountRepository;
import com.mybanksystem.bank.model.entity.Bank;
import com.mybanksystem.bank.model.entity.BankConfiguration;
import com.mybanksystem.bank.model.entity.BankTransferDetails;
import com.mybanksystem.bank.repository.JpaBankConfigurationRepository;
import com.mybanksystem.bank.repository.JpaBankRepository;
import com.mybanksystem.bank.repository.JpaBankTransferDetailsRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

// temporary class
// in memory database to store the Banks
@Component
public class DataHolder {

    private final JpaBankRepository bankRepository;
    private final JpaAccountRepository accountRepository;
    private final JpaBankTransferDetailsRepository bankTransferDetailsRepository;
    private final JpaBankConfigurationRepository bankConfigurationRepository;
    public DataHolder(JpaBankRepository bankRepository, JpaAccountRepository accountRepository, JpaBankTransferDetailsRepository bankTransferDetailsRepository, JpaBankConfigurationRepository bankConfigurationRepository) {
        this.bankRepository = bankRepository;
        this.accountRepository = accountRepository;
        this.bankTransferDetailsRepository = bankTransferDetailsRepository;
        this.bankConfigurationRepository = bankConfigurationRepository;
    }


    public void init() {

        Bank testBank = new Bank("LinkPlus-Bank");
        bankRepository.save(testBank);
        Bank secondBank = new Bank("NLB");
        bankRepository.save(secondBank);

        BankConfiguration bankConfiguration = new BankConfiguration( 10000.00, 5.00, 2, testBank);
        bankConfigurationRepository.save(bankConfiguration);

        BankTransferDetails bankTransferDetails = new BankTransferDetails(testBank);
        bankTransferDetailsRepository.save(bankTransferDetails);

        Account a1 = new Account("Mario", 5.00, testBank);
        Account a2 = new Account("Vojo", 1000.00, testBank);
        Account a3 = new Account("Fico and Ata", 5000.50, testBank);
        accountRepository.save(a1);
        accountRepository.save(a2);
        accountRepository.save(a3);

        //Bank bank = bankRepository.findById(100L).get();
        //bank.setAccounts(new ArrayList<>(List.of(a1, a2, a3)));
        //bankRepository.save(bank);
    }

}
