package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.bank.model.entity.Bank;
import com.mybanksystem.bank.model.entity.BankTransferDetails;
import com.mybanksystem.bank.repository.JpaBankRepository;
import com.mybanksystem.bank.repository.JpaBankTransferDetailsRepository;
import com.mybanksystem.bank.service.CreateBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateBankServiceImpl implements CreateBankService {
    private final JpaBankRepository bankRepository;
    private final JpaBankTransferDetailsRepository bankTransferDetailsRepository;

    @Override
    @Transactional
    public String createNewBank(String bankName) {
        var bank = new Bank(bankName);
        var uuid = UUID.randomUUID().toString();
        bank.setUUID(uuid);
        bankRepository.save(bank);

        var bankTransferDetails = new BankTransferDetails();
        bankTransferDetails.setBank(bank);
        bankTransferDetailsRepository.save(bankTransferDetails);

        return uuid;
    }
}
