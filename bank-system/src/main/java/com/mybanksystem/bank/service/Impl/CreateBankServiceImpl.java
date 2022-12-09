package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.bank.model.entity.Bank;
import com.mybanksystem.bank.model.entity.BankConfiguration;
import com.mybanksystem.bank.model.entity.BankTransferDetails;
import com.mybanksystem.bank.repository.JpaBankConfigurationRepository;
import com.mybanksystem.bank.repository.JpaBankRepository;
import com.mybanksystem.bank.repository.JpaBankTransferDetailsRepository;
import com.mybanksystem.bank.service.CreateBankService;
import org.springframework.stereotype.Service;

@Service
public class CreateBankServiceImpl implements CreateBankService {
    private final JpaBankRepository bankRepository;
    private final JpaBankConfigurationRepository bankConfigurationRepository;
    private final JpaBankTransferDetailsRepository bankTransferDetailsRepository;

    public CreateBankServiceImpl(
            JpaBankRepository bankRepository,
            JpaBankConfigurationRepository bankConfigurationRepository, JpaBankTransferDetailsRepository bankTransferDetailsRepository) {
        this.bankRepository = bankRepository;
        this.bankConfigurationRepository = bankConfigurationRepository;
        this.bankTransferDetailsRepository = bankTransferDetailsRepository;
    }

    @Override
    public void createNewBank(String name, Double thresholdAmount, Double flatFeeAmount, Integer percentFeeAmount) {
        BankConfiguration bankConfiguration = null;
        if (bankConfigurationRepository.findBankConfigurationByThresholdAmountAndFlatFeeAmountAndPercentFeeAmount(
                thresholdAmount,
                flatFeeAmount,
                percentFeeAmount
        ).isPresent()) {
            bankConfiguration = bankConfigurationRepository.findBankConfigurationByThresholdAmountAndFlatFeeAmountAndPercentFeeAmount(
                    thresholdAmount,
                    flatFeeAmount,
                    percentFeeAmount
            ).get();
        } else {
            bankConfiguration = new BankConfiguration(thresholdAmount, flatFeeAmount, percentFeeAmount);
            bankConfigurationRepository.save(bankConfiguration);
        }


        Bank bank = new Bank(name, bankConfiguration);
        bankRepository.save(bank);

        bankTransferDetailsRepository.save(new BankTransferDetails(bank));
    }
}
