package com.mybanksystem.bank.repository;

import com.mybanksystem.bank.model.entity.BankConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaBankConfigurationRepository extends JpaRepository<BankConfiguration, Long> {
    Optional<BankConfiguration> findByBankId(Long bankId);
    Optional<BankConfiguration> findByBankUUID(String bankUUID);
    Optional<BankConfiguration> findBankConfigurationByThresholdAmountAndFlatFeeAmountAndPercentFeeAmount(
            Double thresholdAmount,
            Double flatFeeAmount,
            Integer percent);

}
