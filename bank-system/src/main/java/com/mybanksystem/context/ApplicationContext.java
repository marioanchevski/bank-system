package com.mybanksystem.context;

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
import com.mybanksystem.util.Bean;
import com.mybanksystem.util.Constants;

import java.util.HashMap;
import java.util.Map;

@Deprecated(since = "1.0.1")
public final class ApplicationContext {
    private static Map<Object, Object> applicationContext;

    public ApplicationContext() {
        applicationContext = new HashMap<>();
        initializeRepositories();
        initializeServices();
    }

    public static Object getBeanByName(String name) {
        return applicationContext.get(name);
    }

    public void initializeRepositories() {
        AccountRepository accountRepository = new AccountRepository();
        TransactionRepository transactionRepository = new TransactionRepository();
        BankRepository bankRepository = new BankRepository();

        applicationContext.put(Constants.ACCOUNT_REPOSITORY, accountRepository);
        applicationContext.put(Constants.BANK_REPOSITORY, bankRepository);
        applicationContext.put(Constants.TRANSACTION_REPOSITORY, transactionRepository);
        initializeData(bankRepository, accountRepository);
    }

    public void initializeServices() {
        MapperService mapperService = new MapperServiceImpl();
        FindAccountService findAccountService = new FindAccountServiceImpl((AccountRepository) applicationContext.get(Constants.ACCOUNT_REPOSITORY));
        FindBankService findBankService = new FindBankServiceImpl((BankRepository) applicationContext.get(Constants.BANK_REPOSITORY));
        CreateBankService createBankService = new CreateBankServiceImpl((BankRepository) applicationContext.get(Constants.BANK_REPOSITORY));

        FindAllAccountsInBankService findAllAccountsInBankService =
                new FindAllAccountsInBankServiceImpl((BankRepository) applicationContext.get(Constants.BANK_REPOSITORY));

        FindTransactionsForAccountService findTransactionsForAccountService =
                new FindTransactionsForAccountServiceImpl((TransactionRepository) applicationContext.get(Constants.TRANSACTION_REPOSITORY));

        AccountService accountService = new AccountServiceImpl((TransactionRepository) applicationContext.get(Constants.TRANSACTION_REPOSITORY));

        TransactionPrintingService transactionPrintingService =
                new TransactionPrintingServiceImpl((TransactionRepository) applicationContext.get(Constants.TRANSACTION_REPOSITORY));


        BankPrintingService bankPrintingService = new BankPrintingServiceImpl(findBankService);

        FindAllBanksService findAllBanksService = new FidAllBanksServiceImpl(
                (BankRepository) applicationContext.get(Constants.BANK_REPOSITORY),
                mapperService);

        CreateAccountService createAccountService = new CreateAccountServiceImpl(
                findBankService,
                (AccountRepository) applicationContext.get(Constants.ACCOUNT_REPOSITORY));

        BankService bankService = new BankServiceImpl(
                (TransactionRepository) applicationContext.get(Constants.TRANSACTION_REPOSITORY),
                findBankService,
                accountService);

        AccountPrintingService accountPrintingService = new AccountPrintingServiceImpl(
                findAccountService,
                transactionPrintingService,
                findTransactionsForAccountService);

        TransactionService transactionService = new TransactionServiceImpl(
                (TransactionRepository) applicationContext.get(Constants.TRANSACTION_REPOSITORY),
                findBankService,
                findAccountService);


        applicationContext.put(Constants.MAPPER_SERVICE, mapperService);
        applicationContext.put(Constants.FIND_ACCOUNT_SERVICE, findAccountService);
        applicationContext.put(Constants.FIND_BANK_SERVICE, findBankService);
        applicationContext.put(Constants.CREATE_BANK_SERVICE, createBankService);
        applicationContext.put(Constants.FIND_ALL_ACCOUNTS_IN_BANK_SERVICE, findAllAccountsInBankService);
        applicationContext.put(Constants.FIND_TRANSACTIONS_FOR_ACCOUNT_SERVICE, findTransactionsForAccountService);
        applicationContext.put(Constants.ACCOUNT_SERVICE, accountService);
        applicationContext.put(Constants.TRANSACTION_PRINTING_SERVICE, transactionPrintingService);
        applicationContext.put(Constants.BANK_PRINTING_SERVICE, bankPrintingService);
        applicationContext.put(Constants.FIND_ALL_BANKS_SERVICE, findAllBanksService);
        applicationContext.put(Constants.CREATE_ACCOUNT_SERVICE, createAccountService);
        applicationContext.put(Constants.BANK_SERVICE, bankService);
        applicationContext.put(Constants.ACCOUNT_PRINTING_SERVICE, accountPrintingService);
        applicationContext.put(Constants.TRANSACTION_SERVICE, transactionService);
    }

    private void initializeData(BankRepository br, AccountRepository ar) {
        DataHolder dataHolder = new DataHolder(br, ar);
        dataHolder.init();
    }

}
