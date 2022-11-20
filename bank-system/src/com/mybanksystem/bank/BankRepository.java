package com.mybanksystem.bank;


import java.util.*;

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
       if (bankData.get(bankId) == null)
           return Optional.empty();
       return Optional.of(bankData.get(bankId));
    }

    private Long generateBankId() {
        return idSeed++;
    }

    public List<Bank> findAll() {
        return new ArrayList<>(bankData.values());
    }
}
