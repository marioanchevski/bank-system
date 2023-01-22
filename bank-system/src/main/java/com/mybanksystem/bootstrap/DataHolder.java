package com.mybanksystem.bootstrap;


import com.mybanksystem.account.model.Account;
import com.mybanksystem.account.JpaAccountRepository;
import com.mybanksystem.bank.model.BankConfigurationGroupType;
import com.mybanksystem.bank.model.entity.Bank;
import com.mybanksystem.bank.model.entity.BankConfiguration;
import com.mybanksystem.bank.model.entity.BankTransferDetails;
import com.mybanksystem.bank.repository.JpaBankConfigurationRepository;
import com.mybanksystem.bank.repository.JpaBankRepository;
import com.mybanksystem.bank.repository.JpaBankTransferDetailsRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

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

    @PostConstruct
    public void init() {
        Bank testBank = new Bank("LinkPlus-Bank");
        testBank.setUUID("1");
        bankRepository.save(testBank);
        Bank secondBank = new Bank("NLB");
        secondBank.setUUID(UUID.randomUUID().toString());
        bankRepository.save(secondBank);

        BankConfiguration bankConfiguration = new BankConfiguration(BigDecimal.valueOf(10000.00), BigDecimal.valueOf(5.00), BigInteger.valueOf(2));
        bankConfiguration.setBank(testBank);
        bankConfigurationRepository.save(bankConfiguration);

        //testBank.setBankConfiguration(bankConfiguration);
        bankRepository.save(testBank);


        BankTransferDetails bankTransferDetails = new BankTransferDetails();
        bankTransferDetails.setBank(testBank);
        bankTransferDetailsRepository.save(bankTransferDetails);

        BankTransferDetails nlb = new BankTransferDetails();
        nlb.setBank(secondBank);
        bankTransferDetailsRepository.save(nlb);

        Account a1 = new Account("Mario", BigDecimal.valueOf(5.00), testBank);
        a1.setUUID("1");
        Account a2 = new Account("Vojo", BigDecimal.valueOf(1000.00), testBank);
        a2.setUUID("2");
        Account a3 = new Account("Fico and Ata", BigDecimal.valueOf(5000.50), testBank);
        a3.setUUID(UUID.randomUUID().toString());
        Account a4 = new Account("Random user", BigDecimal.valueOf(23), secondBank);
        a4.setUUID(UUID.randomUUID().toString());
        accountRepository.save(a1);
        accountRepository.save(a2);
        accountRepository.save(a3);
        accountRepository.save(a4);

        //Bank bank = bankRepository.findById(100L).get();
        //bank.setAccounts(new ArrayList<>(List.of(a1, a2, a3)));
        //bankRepository.save(bank);
    }

}
