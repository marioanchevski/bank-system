package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.bank.model.entity.Bank;
import com.mybanksystem.bank.repository.JpaBankRepository;
import com.mybanksystem.bank.model.exceptions.NonExistentBankException;
import com.mybanksystem.bank.service.FindBankService;
import org.springframework.stereotype.Service;

@Service
public class FindBankServiceImpl implements FindBankService {
    private final JpaBankRepository bankRepository;

    public FindBankServiceImpl(JpaBankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public Bank findBankById(Long bankId) throws NonExistentBankException {
        return bankRepository.findById(bankId)
                .orElseThrow(() -> new NonExistentBankException(bankId));
    }
}
