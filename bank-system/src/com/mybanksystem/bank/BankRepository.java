package com.mybanksystem.bank;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BankRepository {
    private static Long idSeed = 100L;
    private Map<Long, Bank> bankData;

    public BankRepository() {
        bankData = new HashMap<>();
        saveBank(new Bank("LinkPlus-Bank", 10.00, 2, 10000.00));
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
}
