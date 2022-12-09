package com.mybanksystem.components;

import com.mybanksystem.account.model.exceptions.NonExistentAccountException;
import com.mybanksystem.account.service.AccountPrintingService;
import com.mybanksystem.account.service.CreateAccountService;
import com.mybanksystem.bank.model.exceptions.NonExistentBankException;
import com.mybanksystem.bank.service.FindBankService;
import com.mybanksystem.util.Constants;
import com.mybanksystem.util.ValidationUtil;
import org.springframework.stereotype.Component;

@Component
public class AccountComponents {
    private static FindBankService findBankService;
    private static CreateAccountService createAccountService;
    private static AccountPrintingService accountPrintingService;


    public AccountComponents(FindBankService findBankService, CreateAccountService createAccountService, AccountPrintingService accountPrintingService) {
        this.findBankService=findBankService;
        this.createAccountService=createAccountService;
        this.accountPrintingService=accountPrintingService;
    }

    public static void createNewAccount() {
        BankComponents.displayBanks();
        Long bankId = ValidationUtil.userInputIdNumberInCorrectFormat(Constants.BANK_NAME_MSG);
        try {
            findBankService.findBankById(bankId);
            String name = ValidationUtil.userInputNonBlankName(Constants.ACCOUNT_NAME_MSG);
            double balance = ValidationUtil.userInputAmountInCorrectFormat(Constants.AMOUNT_BALANCE_MSG);
            System.out.println("Account created.");
            createAccountService.addAccountToBank(name, balance, bankId);
        } catch (NonExistentBankException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void displayAccountDetails() {
        Long idFrom = ValidationUtil.userInputIdNumberInCorrectFormat(Constants.ACCOUNT_NAME_MSG);
        try {
            accountPrintingService.printAccountDetails(idFrom);
        } catch (NonExistentAccountException e) {
            System.out.println(e.getMessage());
        }
    }


}
