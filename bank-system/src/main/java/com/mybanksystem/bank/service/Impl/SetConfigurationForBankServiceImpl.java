package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.bank.model.BankConfigurationGroupType;
import com.mybanksystem.bank.model.dto.BankDTO;
import com.mybanksystem.bank.model.entity.BankConfiguration;
import com.mybanksystem.bank.model.exceptions.NonExistentBankException;
import com.mybanksystem.bank.repository.JpaBankConfigurationRepository;
import com.mybanksystem.bank.repository.JpaBankRepository;
import com.mybanksystem.bank.service.SetConfigurationForBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;

@RequiredArgsConstructor
@Service
public class SetConfigurationForBankServiceImpl implements SetConfigurationForBankService {
    private final JpaBankRepository bankRepository;
    private final JpaBankConfigurationRepository bankConfigurationRepository;

    @Override
    @Transactional
    public BankDTO createAndSetBankConfiguration(String bankUUID, BankConfigurationGroupType group) {
        var bank = bankRepository.findBankByUUID(bankUUID)
                .orElseThrow(() -> new NonExistentBankException(bankUUID));

        BankConfiguration bankConfiguration = null;
        if (group == BankConfigurationGroupType.EU) {
            bankConfiguration = new BankConfiguration(BigDecimal.valueOf(10000.00), BigDecimal.valueOf(5.00), BigInteger.valueOf(2));
        } else if (group == BankConfigurationGroupType.US) {
            bankConfiguration = new BankConfiguration(BigDecimal.valueOf(15000.00), BigDecimal.valueOf(10.00), BigInteger.valueOf(10));
        }
        bankConfiguration.setBank(bank);

        bankConfigurationRepository.save(bankConfiguration);
        return new BankDTO(bank.getName(), bankUUID);
    }
}
