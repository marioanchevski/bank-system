package com.mybanksystem.components;

import com.mybanksystem.bank.BankRepository;
import com.mybanksystem.bank.exceptions.NonExistentBankException;
import com.mybanksystem.bank.service.*;
import com.mybanksystem.util.Constants;
import com.mybanksystem.util.ValidationUtil;
import org.springframework.stereotype.Component;

@Component
public class BankComponents {
    private static BankRepository bankRepository;
    private static FindAllBanksService findAllBanksService;
    private static CreateBankService createBankService;
    private static FindAllAccountsInBankService findAllAccountsInBankService;
    private static BankPrintingService bankPrintingService;
    private static FindBankService findBankService;

    public BankComponents(BankRepository bankRepository,
                          FindAllBanksService findAllBanksService,
                          CreateBankService createBankService,
                          FindAllAccountsInBankService findAllAccountsInBankService,
                          BankPrintingService bankPrintingService,
                          FindBankService findBankService){
        this.bankRepository=bankRepository;
        this.findAllBanksService=findAllBanksService;
        this.createBankService=createBankService;
        this.findAllAccountsInBankService=findAllAccountsInBankService;
        this.bankPrintingService=bankPrintingService;
        this.findBankService =findBankService;
    }


    public static void createNewBank() {
        String bankName = ValidationUtil.userInputNonBlankName(Constants.BANK_NAME_MSG);
        Double flatFee = ValidationUtil.userInputAmountInCorrectFormat(Constants.FLAT_AMOUNT_MSG);
        Integer percentFee = ValidationUtil.userInputFlatPercentInCorrectFormat();
        Double thresholdAmount = ValidationUtil.userInputAmountInCorrectFormat(Constants.FLAT_PERCENT_MSG);
        createBankService.createNewBank(bankName, thresholdAmount, flatFee, percentFee);
        System.out.println(bankName + " successfully created.");
    }

    public static void displayBanks() {
        findAllBanksService.findAllBanks().forEach(System.out::println);
    }

    public static void displayAccountsInBank() {
        BankComponents.displayBanks();
        Long bankId = ValidationUtil.userInputIdNumberInCorrectFormat(Constants.BANK_NAME_MSG);
        try {
            findAllAccountsInBankService.findAll(bankId).forEach(System.out::println);
        } catch (NonExistentBankException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void displayBankDetails() {
        BankComponents.displayBanks();
        Long bankId = ValidationUtil.userInputIdNumberInCorrectFormat(Constants.BANK_NAME_MSG);
        try {
            System.out.println(bankPrintingService.printBankDetails(bankId));
        } catch (NonExistentBankException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void displayTransactionFee() {
        BankComponents.displayBanks();
        Long bankId = ValidationUtil.userInputIdNumberInCorrectFormat(Constants.BANK_NAME_MSG);
        try {
            System.out.format("Total transaction fee: %10.2f$",
                    findBankService.findBankById(bankId).getTotalTransactionFeeAmount());
        } catch (NonExistentBankException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void displayTransferAmount() {
        BankComponents.displayBanks();
        Long bankId = ValidationUtil.userInputIdNumberInCorrectFormat(Constants.BANK_NAME_MSG);
        try {
            System.out.format("Total transfer amount: %10.2f$",
                    findBankService.findBankById(bankId).getTotalTransferAmount());
        } catch (NonExistentBankException e) {
            System.out.println(e.getMessage());
        }
    }
}
