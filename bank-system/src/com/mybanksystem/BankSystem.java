package com.mybanksystem;

import com.mybanksystem.account.service.AccountPrintingService;
import com.mybanksystem.account.service.CreateAccountService;
import com.mybanksystem.bank.service.*;
import com.mybanksystem.account.exceptions.InsufficientFundsException;
import com.mybanksystem.account.exceptions.NonExistentAccountException;
import com.mybanksystem.bank.exceptions.NonExistentBankException;
import com.mybanksystem.transaction.*;
import com.mybanksystem.transaction.service.TransactionService;
import com.mybanksystem.util.ApplicationContext;
import com.mybanksystem.util.Bean;
import com.mybanksystem.util.UserInputMapper;
import com.mybanksystem.util.ValidationUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankSystem {
    private static Map<String, Bean> applicationContext = new HashMap<>();

    private static void createNewBank() {
        String bankName = ValidationUtil.userInputNonBlankName(ValidationUtil.BANK_NAME_MSG);
        Double flatFee = ValidationUtil.userInputAmountInCorrectFormat(ValidationUtil.FLAT_AMOUNT_MSG);
        Integer percentFee = ValidationUtil.userInputFlatPercentInCorrectFormat();
        Double thresholdAmount = ValidationUtil.userInputAmountInCorrectFormat(ValidationUtil.FLAT_PERCENT_MSG);
        CreateBankService createBankService = (CreateBankService) applicationContext.get(ApplicationContext.CREATE_BANK_SERVICE);
        createBankService.createNewBank(bankName, thresholdAmount, flatFee, percentFee);
        System.out.println(bankName + " successfully created.");
    }

    private static void displayBanks() {
        FindAllBanksService findAllBanksService = (FindAllBanksService) applicationContext.get(ApplicationContext.FIND_ALL_BANKS_SERVICE);
        findAllBanksService.findAllBanks().forEach(System.out::println);
    }

    private static void createNewAccount() {
        displayBanks();
        Long bankId = ValidationUtil.userInputIdNumberInCorrectFormat(ValidationUtil.BANK_NAME_MSG);
        FindBankService findBankService = (FindBankService) applicationContext.get(ApplicationContext.FIND_BANK_SERVICE);
        CreateAccountService createAccountService = (CreateAccountService) applicationContext.get(ApplicationContext.CREATE_ACCOUNT_SERVICE);
        try {
            findBankService.findBankById(bankId);
            String name = ValidationUtil.userInputNonBlankName(ValidationUtil.ACCOUNT_NAME_MSG);
            double balance = ValidationUtil.userInputAmountInCorrectFormat(ValidationUtil.AMOUNT_BALANCE_MSG);
            createAccountService.addAccountToBank(name, balance, bankId);
        } catch (NonExistentBankException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void makeNewTransaction() {
        displayBanks();

        Long bankId = ValidationUtil.userInputIdNumberInCorrectFormat(ValidationUtil.BANK_NAME_MSG);
        FindBankService findBankService = (FindBankService) applicationContext.get(ApplicationContext.FIND_BANK_SERVICE);
        try {
            findBankService.findBankById(bankId);
            ValidationUtil.showTransactionMenu();
            TransactionType transactionType = ValidationUtil.transactionMenuDecision();

            long idFrom, idTo;
            if (transactionType != null) {
                idFrom = ValidationUtil.userInputIdNumberInCorrectFormat(ValidationUtil.ACCOUNT_NAME_MSG);

                if (transactionType == TransactionType.NORMAL) {
                    idTo = ValidationUtil.userInputIdNumberInCorrectFormat(ValidationUtil.ACCOUNT_NAME_MSG);
                    if (idFrom == idTo) {
                        transactionType = TransactionType.DEPOSIT;
                    }
                } else {
                    idTo = idFrom;
                }
                double amount = ValidationUtil.userInputAmountInCorrectFormat(ValidationUtil.TRANSACTION_AMOUNT_MSG);
                TransactionContext context = new TransactionContext(transactionType, idFrom, idTo, amount, bankId);

                TransactionService transactionService = (TransactionService) applicationContext.get(ApplicationContext.TRANSACTION_SERVICE);
                BankService bankService = (BankService) applicationContext.get(ApplicationContext.BANK_SERVICE);

                String transactionId = transactionService.createTransaction(context);
                try {
                    bankService.makeTransaction(transactionId, bankId);
                    System.out.println("Transaction successful.");
                } catch (InsufficientFundsException |
                         NonExistentBankException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Transaction failed.");
                }
            }
        } catch (NonExistentBankException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayAccountsInBank() {
        displayBanks();
        Long bankId = ValidationUtil.userInputIdNumberInCorrectFormat(ValidationUtil.BANK_NAME_MSG);
        FindAllAccountsInBankService findAllAccountsInBankService =
                (FindAllAccountsInBankService) applicationContext.get(ApplicationContext.FIND_ALL_ACCOUNTS_IN_BANK_SERVICE);
        try {
            findAllAccountsInBankService.findAll(bankId).forEach(System.out::println);
        } catch (NonExistentBankException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayAccountDetails() {
        Long idFrom = ValidationUtil.userInputIdNumberInCorrectFormat(ValidationUtil.ACCOUNT_NAME_MSG);
        AccountPrintingService accountPrintingService = (AccountPrintingService) applicationContext.get(ApplicationContext.ACCOUNT_PRINTING_SERVICE);
        try {
            accountPrintingService.printAccountDetails(idFrom);
        } catch (NonExistentAccountException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayBankDetails() {
        displayBanks();
        Long bankId = ValidationUtil.userInputIdNumberInCorrectFormat(ValidationUtil.BANK_NAME_MSG);
        BankPrintingService bankPrintingService = (BankPrintingService) applicationContext.get(ApplicationContext.BANK_PRINTING_SERVICE);
        try {
            System.out.println(bankPrintingService.printBankDetails(bankId));
        } catch (NonExistentBankException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayTransactionFee() {
        displayBanks();
        Long bankId = ValidationUtil.userInputIdNumberInCorrectFormat(ValidationUtil.BANK_NAME_MSG);
        FindBankService findBankService = (FindBankService) applicationContext.get(ApplicationContext.FIND_BANK_SERVICE);
        try {
            System.out.format("Total transaction fee: %10.2f$",
                    findBankService.findBankById(bankId).getTotalTransactionFeeAmount());
        } catch (NonExistentBankException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayTransferAmount() {
        displayBanks();
        Long bankId = ValidationUtil.userInputIdNumberInCorrectFormat(ValidationUtil.BANK_NAME_MSG);
        FindBankService findBankService = (FindBankService) applicationContext.get(ApplicationContext.FIND_BANK_SERVICE);
        try {
            System.out.format("Total transfer amount: %10.2f$",
                    findBankService.findBankById(bankId).getTotalTransferAmount());
        } catch (NonExistentBankException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApplicationContext.initializeRepositories(applicationContext);
        ApplicationContext.initializeServices(applicationContext);

        while (true) {
            ValidationUtil.showMenu();
            String decision = scanner.nextLine();
            switch (UserInputMapper.mapUserInputToMenuOption(decision)) {
                case CREATE_A_NEW_BANK:
                    createNewBank();
                    break;
                case SEE_LIST_OF_BANKS:
                    displayBanks();
                    break;
                case CREATE_A_NEW_ACCOUNT:
                    createNewAccount();
                    break;
                case MAKE_A_NEW_TRANSACTION:
                    makeNewTransaction();
                    break;
                case SEE_LIST_OF_ACCOUNTS:
                    displayAccountsInBank();
                    break;
                case CHECK_ACCOUNT_DETAILS:
                    displayAccountDetails();
                    break;
                case CHECK_BANK_DETAILS:
                    displayBankDetails();
                    break;
                case CHECK_TOTAL_TRANSACTION_FEE_AMOUNT:
                    displayTransactionFee();
                    break;
                case CHECK_TOTAL_BANK_TRANSFER_AMOUNT:
                    displayTransferAmount();
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
