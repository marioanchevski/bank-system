package com.mybanksystem.util;

public class Constants {
    public static final String ACCOUNT_REPOSITORY = "accountRepository";
    public static final String BANK_REPOSITORY = "bankRepository";
    public static final String TRANSACTION_REPOSITORY = "transactionRepository";
    public static final String MAPPER_SERVICE = "mapperService";
    public static final String FIND_ACCOUNT_SERVICE = "findAccountService";
    public static final String FIND_BANK_SERVICE = "findBankService";
    public static final String CREATE_BANK_SERVICE = "createBankService";
    public static final String FIND_ALL_ACCOUNTS_IN_BANK_SERVICE = "findAllAccountsInBankService";
    public static final String FIND_TRANSACTIONS_FOR_ACCOUNT_SERVICE = "findTransactionsForAccountService";
    public static final String ACCOUNT_SERVICE = "accountService";
    public static final String TRANSACTION_PRINTING_SERVICE = "transactionPrintingService";
    public static final String BANK_PRINTING_SERVICE = "bankPrintingService";
    public static final String FIND_ALL_BANKS_SERVICE = "findAllBanksService";
    public static final String CREATE_ACCOUNT_SERVICE = "createAccountService";
    public static final String BANK_SERVICE = "bankService";
    public static final String ACCOUNT_PRINTING_SERVICE = "accountPrintingService";
    public static final String TRANSACTION_SERVICE = "transactionService";

    public final static int AMOUNT_BALANCE_MSG = 0;
    public final static int TRANSACTION_AMOUNT_MSG = 1;
    public final static int FLAT_AMOUNT_MSG = 2;
    public final static int FLAT_PERCENT_MSG = 3;
    public final static int ACCOUNT_NAME_MSG = 0;
    public final static int BANK_NAME_MSG = 1;
    public final static String CORRECT_AMOUNT_FORMAT_REGEX = "[0-9]+.[0-9]{2}\\$";
    public final static String CORRECT_ID_NUMBER_FORMAT_REGEX = "[0-9]+";
    public final static String CORRECT_FLAT_PERCENT_FORMAT_REGEX = "^[0-9]{1,2}$";
}
