package com.mybanksystem.util;

import com.mybanksystem.account.AccountRepository;
import com.mybanksystem.account.service.*;
import com.mybanksystem.account.service.Impl.*;
import com.mybanksystem.bank.BankRepository;
import com.mybanksystem.bank.service.*;
import com.mybanksystem.bank.service.Impl.*;
import com.mybanksystem.bootstrap.DataHolder;
import com.mybanksystem.transaction.TransactionRepository;
import com.mybanksystem.transaction.service.Impl.TransactionPrintingServiceImpl;
import com.mybanksystem.transaction.service.Impl.TransactionServiceImpl;
import com.mybanksystem.transaction.service.TransactionPrintingService;
import com.mybanksystem.transaction.service.TransactionService;

import java.util.Map;

public class ApplicationContext {
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


    public static void initializeRepositories(Map<String, Bean> context) {
        AccountRepository accountRepository = new AccountRepository();
        TransactionRepository transactionRepository = new TransactionRepository();
        BankRepository bankRepository = new BankRepository();

        context.put(ACCOUNT_REPOSITORY, accountRepository);
        context.put(BANK_REPOSITORY, bankRepository);
        context.put(TRANSACTION_REPOSITORY, transactionRepository);
        initializeData(bankRepository, accountRepository);
    }

    public static void initializeServices(Map<String, Bean> context) {
        MapperService mapperService = new MapperServiceImpl();
        FindAccountService findAccountService = new FindAccountServiceImpl((AccountRepository) context.get(ACCOUNT_REPOSITORY));
        FindBankService findBankService = new FindBankServiceImpl((BankRepository) context.get(BANK_REPOSITORY));
        CreateBankService createBankService = new CreateBankServiceImpl((BankRepository) context.get(BANK_REPOSITORY));

        FindAllAccountsInBankService findAllAccountsInBankService =
                new FindAllAccountsInBankServiceImpl((BankRepository) context.get(BANK_REPOSITORY));

        FindTransactionsForAccountService findTransactionsForAccountService =
                new FindTransactionsForAccountServiceImpl((TransactionRepository) context.get(TRANSACTION_REPOSITORY));

        AccountService accountService = new AccountServiceImpl((TransactionRepository) context.get(TRANSACTION_REPOSITORY));

        TransactionPrintingService transactionPrintingService =
                new TransactionPrintingServiceImpl((TransactionRepository) context.get(TRANSACTION_REPOSITORY));


        BankPrintingService bankPrintingService = new BankPrintingServiceImpl(findBankService);

        FindAllBanksService findAllBanksService = new FidAllBanksServiceImpl(
                (BankRepository) context.get(BANK_REPOSITORY),
                mapperService);

        CreateAccountService createAccountService = new CreateAccountServiceImpl(
                findBankService,
                (AccountRepository) context.get(ACCOUNT_REPOSITORY));

        BankService bankService = new BankServiceImpl(
                (TransactionRepository) context.get(TRANSACTION_REPOSITORY),
                findBankService,
                accountService);

        AccountPrintingService accountPrintingService = new AccountPrintingServiceImpl(
                findAccountService,
                transactionPrintingService,
                findTransactionsForAccountService);

        TransactionService transactionService = new TransactionServiceImpl(
                (TransactionRepository) context.get(TRANSACTION_REPOSITORY),
                findBankService,
                findAccountService);


        context.put(MAPPER_SERVICE, mapperService);
        context.put(FIND_ACCOUNT_SERVICE, findAccountService);
        context.put(FIND_BANK_SERVICE, findBankService);
        context.put(CREATE_BANK_SERVICE, createBankService);
        context.put(FIND_ALL_ACCOUNTS_IN_BANK_SERVICE, findAllAccountsInBankService);
        context.put(FIND_TRANSACTIONS_FOR_ACCOUNT_SERVICE, findTransactionsForAccountService);
        context.put(ACCOUNT_SERVICE, accountService);
        context.put(TRANSACTION_PRINTING_SERVICE, transactionPrintingService);
        context.put(BANK_PRINTING_SERVICE, bankPrintingService);
        context.put(FIND_ALL_BANKS_SERVICE, findAllBanksService);
        context.put(CREATE_ACCOUNT_SERVICE, createAccountService);
        context.put(BANK_SERVICE, bankService);
        context.put(ACCOUNT_PRINTING_SERVICE, accountPrintingService);
        context.put(TRANSACTION_SERVICE, transactionService);
    }

    private static void initializeData(BankRepository br, AccountRepository ar) {
        DataHolder dataHolder = new DataHolder(br, ar);
        dataHolder.init();
    }

}
