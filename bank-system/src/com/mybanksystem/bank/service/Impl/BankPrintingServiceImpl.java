package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.exceptions.NonExistentBankException;
import com.mybanksystem.bank.service.BankPrintingService;
import com.mybanksystem.bank.service.FindBankService;

public class BankPrintingServiceImpl implements BankPrintingService {
    private final FindBankService findBankService;

    public BankPrintingServiceImpl(FindBankService findBankService) {
        this.findBankService = findBankService;
    }


    @Override
    public String printBankDetails(Long bankId) throws NonExistentBankException {
        Bank bank = findBankService.findBankById(bankId);
        return String.format("Bank: %s\nFlatFee: %.2f$ \nPercentFee: %d%%\nThresholdAmount: %.2f$\n",
                bank.getName(), bank.getFlatFeeAmount(), bank.getPercentFeeAmount(), bank.getThresholdAmount());
    }

}
