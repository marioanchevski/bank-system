package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.bank.model.dto.BankConfigurationDTO;
import com.mybanksystem.bank.model.dto.BankDTO;
import com.mybanksystem.bank.model.entity.BankConfiguration;
import com.mybanksystem.bank.model.exceptions.NonExistentBankException;
import com.mybanksystem.bank.repository.JpaBankConfigurationRepository;
import com.mybanksystem.bank.service.UpdateBankConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UpdateBankConfigurationServiceImpl implements UpdateBankConfigurationService {
    private final JpaBankConfigurationRepository bankConfigurationRepository;

    @Override
    @Transactional
    public BankConfigurationDTO updateBankConfigurationForBank(String bankUUID, Double thresholdAmount, Double flatFeeAmount, Integer percentFeeAmount) {
        var bankConfiguration = bankConfigurationRepository.findByBankUUID(bankUUID)
                .orElseThrow(() -> new NonExistentBankException(bankUUID));

        if (Objects.nonNull(thresholdAmount)) {

            bankConfiguration.setThresholdAmount(BigDecimal.valueOf(thresholdAmount));
        }

        if (Objects.nonNull(flatFeeAmount)) {
            bankConfiguration.setFlatFeeAmount(BigDecimal.valueOf(flatFeeAmount));
        }
        if (Objects.nonNull(percentFeeAmount)) {
            bankConfiguration.setPercentFeeAmount(BigInteger.valueOf(percentFeeAmount));
        }
        return new BankConfigurationDTO(bankConfiguration.getThresholdAmount().doubleValue(),
                bankConfiguration.getFlatFeeAmount().doubleValue(),
                bankConfiguration.getPercentFeeAmount().intValue());
    }


}
