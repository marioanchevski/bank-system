package com.mybanksystem.bank.repository;

import com.mybanksystem.bank.model.entity.Bank;
import com.mybanksystem.bank.model.entity.BankTransferDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaBankTransferDetailsRepository extends JpaRepository<BankTransferDetails, Long> {
    BankTransferDetails findByBank(Bank bank);
    BankTransferDetails findByBankUUID(String bankUUID);
}
