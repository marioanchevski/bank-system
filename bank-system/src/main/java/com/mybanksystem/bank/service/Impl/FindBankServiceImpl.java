package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.bank.model.dto.BankDTO;
import com.mybanksystem.bank.repository.JpaBankRepository;
import com.mybanksystem.bank.model.exceptions.NonExistentBankException;
import com.mybanksystem.bank.service.FindBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindBankServiceImpl implements FindBankService {
    private final JpaBankRepository bankRepository;

    @Override
    public BankDTO findBankById(String bankUUID) {
        var bank = bankRepository.findBankByUUID(bankUUID)
                .orElseThrow(() -> new NonExistentBankException(bankUUID));
        return new BankDTO(bank.getName(), bankUUID);
    }
}
