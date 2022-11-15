package com.mybanksystem.util;

import com.mybanksystem.bank.Bank;
import com.mybanksystem.transaction.FlatAmountProvisionTransaction;
import com.mybanksystem.transaction.FlatPercentProvisionTransaction;
import com.mybanksystem.transaction.Transaction;
import com.mybanksystem.transaction.TransactionType;

import java.util.Scanner;

public class ValidationUtil {
    public final static int AMOUNT_BALANCE_MSG = 0;
    public final static int TRANSACTION_AMOUNT_MSG = 1;
    public final static int FLAT_AMOUNT_MSG = 2;
    public final static int FLAT_PERCENT_MSG = 3;

    public static void showMenu() {
        System.out.println("\n=============MAIN MENU==============");
        System.out.println("1.Create a new account\n2.Make a new transaction\n3.See list of account's\n4.Check account details\n5.Check bank details" +
                "\n6.Check total transaction fee amount\n7.Check total bank transfer amount\n8.Exit");
        System.out.println("====================================\n");
    }

    public static void showTransactionMenu() {
        System.out.println("\n========TRANSACTION MENU========");
        System.out.println("1.Withdraw\n2.Deposit\n3.Send money to another account\n4.Back");
        System.out.println("================================\n");
    }


    public static double getValidAmountInput(Scanner scanner, int msgtype) {
        String[] promptMsg = {"Enter account balance.",
                "Enter amount.",
                "Enter flat fee amount.",
                "Enter threshold amount.\nIf the amount of a transaction is greater then the threshold amount bank charges you a flat percent, otherwise a flat amount."};
        String[] errMsgs = {"Enter account balance with two decimal places and a dollar sign at the end. Example 15.50$",
                "Enter the amount with two decimal places and a dollar sign at the end. Example 9.50$",
                "Enter the flat fee amount with two decimal places and a dollar sign at the end. Example 7.50$",
                "Enter threshold amount with two decimal places and a dollar sign at the end. Example 10000.00$"};
        String correctAmountFormatRegex = "[0-9]+.[0-9]{2}\\$";
        System.out.println(promptMsg[msgtype]);
        String balanceCheck = scanner.nextLine().trim();
        while (!balanceCheck.matches(correctAmountFormatRegex)) {
            System.out.println(errMsgs[msgtype]);
            balanceCheck = scanner.nextLine().trim();
        }
        balanceCheck = balanceCheck.replace(",", ".");
        return Double.parseDouble(balanceCheck.substring(0, balanceCheck.length() - 1));
    }

    public static long getValidAccountId(Scanner scanner) {
        String numberRegex = "[0-9]+";
        System.out.println("Enter the account id you wish to perform the transaction on.");
        String idFrom = scanner.nextLine().trim();
        while (!idFrom.matches(numberRegex)) {
            System.out.println("The account id must be a number. Plese try again.");
            idFrom = scanner.nextLine().trim();
        }
        return Long.parseLong(idFrom);
    }

    public static String getValidName(Scanner scanner) {
        System.out.println("Enter account owner.");
        String name = scanner.nextLine();
        while (name.trim().isEmpty()) {
            System.out.println("You must provide a name.");
            name = scanner.nextLine().trim();
        }
        return name;
    }

    public static void bankHasNoAccounts() {
        System.out.println("Currently there are no accounts in this bank.\nPlease create a new account.\n");
    }

    public static int getValidFlatPercent(Scanner scanner) {
        String flatPercentRegex = "^[0-9]{1,2}$";
        System.out.println("Please enter a flat percent amount for your bank.");
        String line = scanner.nextLine();
        while (!line.matches(flatPercentRegex)) {
            System.out.println("Flat fee must be whole number. Not greater than 99.");
            line = scanner.nextLine().trim();
        }
        return Integer.parseInt(line);
    }

    public static Bank init(Scanner scanner) {
        System.out.println("\nPlease enter a name for your bank.");
        String bankName = getValidName(scanner);
        double flatFee = getValidAmountInput(scanner, FLAT_AMOUNT_MSG);
        int percentFee = getValidFlatPercent(scanner);
        double thresholdAmount = getValidAmountInput(scanner, FLAT_PERCENT_MSG);
        return new Bank(bankName, flatFee, percentFee, thresholdAmount);
    }
}
