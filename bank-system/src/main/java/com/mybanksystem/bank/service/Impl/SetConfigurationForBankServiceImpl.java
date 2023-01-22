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
import java.util.Map;

@RequiredArgsConstructor
@Service
public class SetConfigurationForBankServiceImpl implements SetConfigurationForBankService {
    private final JpaBankRepository bankRepository;
    private final JpaBankConfigurationRepository bankConfigurationRepository;

    private final Map<BankConfigurationGroupType, BankConfiguration> bankConfigurationMap;

    @Override
    @Transactional
    public BankDTO createAndSetBankConfiguration(String bankUUID, BankConfigurationGroupType group) {

        var bank = bankRepository.findBankByUUID(bankUUID)
                .orElseThrow(() -> new NonExistentBankException(bankUUID));

        BankConfiguration bankConfiguration = bankConfigurationMap.get(group);

        bankConfiguration.setBank(bank);

        bankConfigurationRepository.save(bankConfiguration);
        return new BankDTO(bank.getName(), bankUUID);
    }
}
