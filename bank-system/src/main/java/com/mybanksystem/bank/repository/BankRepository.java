package com.mybanksystem.bank.repository;


import com.mybanksystem.bank.model.entity.Bank;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Deprecated(since = "1.0.1")
public class BankRepository {
    private static Long idSeed = 100L;
    private Map<Long, Bank> bankData;

    public BankRepository() {
        bankData = new HashMap<>();
    }

    public void saveBank(Bank bank) {
        Long bankId = generateBankId();
        bank.setId(bankId);
        bankData.put(bankId, bank);
    }
    public Optional<Bank> findBankById(Long bankId){
        return Optional.ofNullable(bankData.get(bankId));
    }

    private Long generateBankId() {
        return idSeed++;
    }

    public List<Bank> findAll() {
        return new ArrayList<>(bankData.values());
    }
}
