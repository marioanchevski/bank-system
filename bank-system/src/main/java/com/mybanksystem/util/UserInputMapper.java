package com.mybanksystem.util;

public class UserInputMapper {
    public static MenuOption mapUserInputToTransactionMenuOption(String entry) {
        switch (entry) {
            case "1":
                return MenuOption.WITHDRAW;
            case "2":
                return MenuOption.DEPOSIT;
            case "3":
                return MenuOption.NORMAL;
            case "4":
                return MenuOption.BACK;
            default:
                return MenuOption.INVALID_OPTION;
        }
    }
    public static MenuOption mapUserInputToMenuOption(String entry){
        switch (entry) {
            case "1":
                return MenuOption.CREATE_A_NEW_BANK;
            case "2":
                return MenuOption.SEE_LIST_OF_BANKS;
            case "3":
                return MenuOption.CREATE_A_NEW_ACCOUNT;
            case "4":
                return MenuOption.MAKE_A_NEW_TRANSACTION;
            case "5":
                return MenuOption.SEE_LIST_OF_ACCOUNTS;
            case "6":
                return MenuOption.CHECK_ACCOUNT_DETAILS;
            case "7":
                return MenuOption.CHECK_BANK_DETAILS;
            case "8":
                return MenuOption.CHECK_TOTAL_TRANSACTION_FEE_AMOUNT;
            case "9":
                return MenuOption.CHECK_TOTAL_BANK_TRANSFER_AMOUNT;
            case "0":
                return MenuOption.EXIT;
            default:
                return MenuOption.INVALID_OPTION;
        }
    }
}
