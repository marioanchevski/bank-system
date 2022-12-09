package com.mybanksystem;

import com.mybanksystem.bank.repository.JpaBankRepository;
import com.mybanksystem.components.AccountComponents;
import com.mybanksystem.components.BankComponents;
import com.mybanksystem.components.TransactionComponents;
import com.mybanksystem.util.UserInputMapper;
import com.mybanksystem.util.ValidationUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
@SpringBootApplication
public class BankSystem {

    public static void main(String[] args) {
        SpringApplication.run(BankSystem.class, args);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            ValidationUtil.showMenu();
            String decision = scanner.nextLine();
            switch (UserInputMapper.mapUserInputToMenuOption(decision)) {
                case CREATE_A_NEW_BANK:
                    BankComponents.createNewBank();
                    break;
                case SEE_LIST_OF_BANKS:
                    BankComponents.displayBanks();
                    break;
                case CREATE_A_NEW_ACCOUNT:
                    AccountComponents.createNewAccount();
                    break;
                case MAKE_A_NEW_TRANSACTION:
                    TransactionComponents.makeNewTransaction();
                    break;
                case SEE_LIST_OF_ACCOUNTS:
                    BankComponents.displayAccountsInBank();
                    break;
                case CHECK_ACCOUNT_DETAILS:
                    AccountComponents.displayAccountDetails();
                    break;
                case CHECK_BANK_DETAILS:
                    BankComponents.displayBankDetails();
                    break;
                case CHECK_TOTAL_TRANSACTION_FEE_AMOUNT:
                    BankComponents.displayTransactionFee();
                    break;
                case CHECK_TOTAL_BANK_TRANSFER_AMOUNT:
                    BankComponents.displayTransferAmount();
                    break;
                case EXIT:
                    System.out.println("Thank you for using our services.");
                    return;
                default:
                    System.out.println("Please select a valid option.");
                    break;
            }
        }

    }
}
