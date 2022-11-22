package com.mybanksystem.util;

import com.mybanksystem.transaction.TransactionType;

import java.util.Scanner;

public class ValidationUtil {
    static Scanner scanner = new Scanner(System.in);
    public final static int AMOUNT_BALANCE_MSG = 0;
    public final static int TRANSACTION_AMOUNT_MSG = 1;
    public final static int FLAT_AMOUNT_MSG = 2;
    public final static int FLAT_PERCENT_MSG = 3;
    public final static int ACCOUNT_NAME_MSG = 0;
    public final static int BANK_NAME_MSG = 1;
    public final static String CORRECT_AMOUNT_FORMAT_REGEX = "[0-9]+.[0-9]{2}\\$";
    public final static String CORRECT_ID_NUMBER_FORMAT_REGEX = "[0-9]+";
    public final static String CORRECT_FLAT_PERCENT_FORMAT_REGEX = "^[0-9]{1,2}$";


    public static void showMenu() {
        System.out.println("\n=============MAIN MENU==============");
        System.out.println("1.Create a new bank\n2.See list of bank's\n3.Create a new account\n4.Make a new transaction\n5.See list of account's\n6.Check account details\n7.Check bank details" +
                "\n8.Check total transaction fee amount\n9.Check total bank transfer amount\n0.Exit");
        System.out.println("====================================\n");
    }

    public static void showTransactionMenu() {
        System.out.println("\n========TRANSACTION MENU========");
        System.out.println("1.Withdraw\n2.Deposit\n3.Send money to another account\n4.Back");
        System.out.println("================================\n");
    }


    public static double userInputAmountInCorrectFormat(int msgType) {
        String[] promptMsg = {"Enter account balance.",
                "Enter amount.",
                "Enter flat fee amount.",
                "Enter threshold amount.\nIf the amount of a transaction is greater then the threshold amount bank charges you a flat percent, otherwise a flat amount."};
        String[] errMsgs = {"Enter account balance with two decimal places and a dollar sign at the end. Example 15.50$",
                "Enter the amount with two decimal places and a dollar sign at the end. Example 9.50$",
                "Enter the flat fee amount with two decimal places and a dollar sign at the end. Example 7.50$",
                "Enter threshold amount with two decimal places and a dollar sign at the end. Example 10000.00$"};
        System.out.println(promptMsg[msgType]);
        String balanceCheck = scanner.nextLine().trim();
        while (!balanceCheck.matches(CORRECT_AMOUNT_FORMAT_REGEX)) {
            System.out.println(errMsgs[msgType]);
            balanceCheck = scanner.nextLine().trim();
        }
        balanceCheck = balanceCheck.replace(",", ".");
        return Double.parseDouble(balanceCheck.substring(0, balanceCheck.length() - 1));
    }


    public static long userInputIdNumberInCorrectFormat(int msgType) {
        String[] promptMsg = {"Please enter the bank Id number.",
                "Enter the account Id you wish to perform the transaction on."};
        String[] errMsgs = {
                "The bank id must be a number. Please try again.",
                "The account id must be a number. Please try again."
        };
        System.out.println(promptMsg[msgType]);
        String idFrom = scanner.nextLine().trim();
        while (!idFrom.matches(CORRECT_ID_NUMBER_FORMAT_REGEX)) {
            System.out.println(errMsgs[msgType]);
            idFrom = scanner.nextLine().trim();
        }
        return Long.parseLong(idFrom);
    }


    public static String userInputNonBlankName(int msgType) {
        String[] promptMsg = {"Enter account owner.",
                "Enter bank name."};
        System.out.println(promptMsg[msgType]);
        String name = scanner.nextLine();
        while (name.trim().isEmpty()) {
            System.out.println("You must provide a name.");
            name = scanner.nextLine().trim();
        }
        return name;
    }

    public static int userInputFlatPercentInCorrectFormat() {
        System.out.println("Please enter a flat percent amount for your bank.");
        String line = scanner.nextLine();
        while (!line.matches(CORRECT_FLAT_PERCENT_FORMAT_REGEX)) {
            System.out.println("Flat fee must be whole number. Not greater than 99.");
            line = scanner.nextLine().trim();
        }
        return Integer.parseInt(line);
    }

    public static TransactionType transactionMenuDecision() {

        String transactionMenuDecision = scanner.nextLine().trim();
        switch (UserInputMapper.mapUserInputToTransactionMenuOption(transactionMenuDecision)) {
            case WITHDRAW:
                return TransactionType.WITHDRAW;
            case DEPOSIT:
                return TransactionType.DEPOSIT;
            case NORMAL:
                return TransactionType.NORMAL;
            default:
                return null;
        }
    }

}
