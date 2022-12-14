package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.bank.model.entity.Bank;
import com.mybanksystem.bank.model.entity.BankConfiguration;
import com.mybanksystem.bank.model.exceptions.NonExistentBankException;
import com.mybanksystem.bank.repository.JpaBankConfigurationRepository;
import com.mybanksystem.bank.repository.JpaBankRepository;
import com.mybanksystem.bank.service.BankPrintingService;
import org.springframework.stereotype.Service;

@Service
public class BankPrintingServiceImpl implements BankPrintingService {
    private final JpaBankRepository bankRepository;
    private final JpaBankConfigurationRepository bankConfigurationRepository;

    public BankPrintingServiceImpl(JpaBankRepository bankRepository, JpaBankConfigurationRepository bankConfigurationRepository) {
        this.bankRepository = bankRepository;
        this.bankConfigurationRepository = bankConfigurationRepository;
    }

    @Override
    public String printBankDetails(Long bankId) throws NonExistentBankException {

        BankConfiguration bankConfiguration = bankConfigurationRepository.findByBankId(bankId)
                .orElseThrow(()-> new NonExistentBankException(bankId));
        Bank bank = bankRepository.findById(bankConfiguration.getBank().getId()).get();

        return String.format("Bank: %s\nFlatFee: %.2f$ \nPercentFee: %d%%\nThresholdAmount: %.2f$\n",
                bank.getName(),
                bankConfiguration.getFlatFeeAmount(),
                bankConfiguration.getPercentFeeAmount(),
                bankConfiguration.getThresholdAmount());
    }

}
