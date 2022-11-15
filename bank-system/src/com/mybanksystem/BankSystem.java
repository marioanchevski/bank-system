package com.mybanksystem;

import com.mybanksystem.account.AccountRepository;
import com.mybanksystem.account.service.AccountService;
import com.mybanksystem.account.service.CreateAccountService;
import com.mybanksystem.account.service.FindAccountService;
import com.mybanksystem.account.service.Impl.AccountServiceImpl;
import com.mybanksystem.account.service.Impl.AccountPrintingServiceImpl;
import com.mybanksystem.account.service.AccountPrintingService;
import com.mybanksystem.account.service.Impl.CreateAccountServiceImpl;
import com.mybanksystem.account.service.Impl.FindAccountServiceImpl;
import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.BankRepository;
import com.mybanksystem.bank.service.BankPrintingService;
import com.mybanksystem.bank.service.BankService;
import com.mybanksystem.bank.service.FindBankService;
import com.mybanksystem.bank.service.Impl.BankPrintingServiceImpl;
import com.mybanksystem.bank.service.Impl.BankServiceImpl;
import com.mybanksystem.account.exceptions.InsufficientFundsException;
import com.mybanksystem.account.exceptions.NonExistentAccountException;
import com.mybanksystem.bank.exceptions.NonExistentBankException;
import com.mybanksystem.bank.service.Impl.FindBankServiceImpl;
import com.mybanksystem.bootstrap.DataHolder;
import com.mybanksystem.transaction.*;
import com.mybanksystem.util.ValidationUtil;

import java.util.Scanner;

public class BankSystem {

    public static void main(String[] args) {
        // testing purposes
        Long bankId = 100L;
        Scanner scanner = new Scanner(System.in);
        AccountRepository accountRepository = new AccountRepository();
        FindAccountService findAccountService = new FindAccountServiceImpl(accountRepository);
        TransactionRepository transactionRepository = new TransactionRepository();
        BankRepository bankRepository = new BankRepository();
        DataHolder dataHolder = new DataHolder(bankRepository, accountRepository);
        dataHolder.init();
        AccountService accountService = new AccountServiceImpl(findAccountService, transactionRepository);
        AccountPrintingService printAccountService = new AccountPrintingServiceImpl(bankRepository, findAccountService);
        FindBankService findBankService = new FindBankServiceImpl(bankRepository);
        BankService bankService = new BankServiceImpl(transactionRepository, findBankService, accountService, findAccountService);
        BankPrintingService bankPrintingService = new BankPrintingServiceImpl(findBankService);
        TransactionService transactionService = new TransactionServiceImpl(transactionRepository, findBankService);
        CreateAccountService createAccountService = new CreateAccountServiceImpl(findBankService, accountRepository);

        //Bank bank = init(scanner);

        try {
            System.out.println(bankPrintingService.printBankDetails(bankId));
        } catch (NonExistentBankException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            ValidationUtil.showMenu();
            String decision = scanner.nextLine();
            switch (decision) {
                case "1":
                    String name = ValidationUtil.getValidName(scanner);
                    double balance = ValidationUtil.getValidAmountInput(scanner, ValidationUtil.AMOUNT_BALANCE_MSG);

                    try {
                        createAccountService.addAccountToBank(name, balance, bankId);
                    } catch (NonExistentBankException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":

                    if (findBankService.findBankById(bankId).get().getAccounts().isEmpty()) {
                        ValidationUtil.bankHasNoAccounts();
                        break;
                    }
                    ValidationUtil.showTransactionMenu();
                    TransactionType transactionType = null;

                    String transactionMenuDecision = scanner.nextLine().trim();
                    boolean backOptionOrInvalidInput = false;
                    switch (transactionMenuDecision) {
                        case "1":
                            transactionType = TransactionType.WITHDRAW;
                            break;
                        case "2":
                            transactionType = TransactionType.DEPOSIT;
                            break;
                        case "3":
                            transactionType = TransactionType.NORMAL;
                            break;
                        case "4":
                            backOptionOrInvalidInput = true;
                            break;
                        default:
                            System.out.println("Please select a valid option.");
                            backOptionOrInvalidInput = true;
                            break;
                    }

                    long idFrom, idTo;
                    if (!backOptionOrInvalidInput) {
                        idFrom = ValidationUtil.getValidAccountId(scanner);

                        if (transactionType == TransactionType.NORMAL) {
                            idTo = ValidationUtil.getValidAccountId(scanner);
                            if (idFrom == idTo)
                                transactionType = TransactionType.DEPOSIT;
                        } else
                            idTo = idFrom;
                        double amount = ValidationUtil.getValidAmountInput(scanner, ValidationUtil.TRANSACTION_AMOUNT_MSG);
                        TransactionContext context = new TransactionContext(transactionType, idFrom, idTo, amount, bankId);

                        String transactionId = transactionService.createTransaction(context);
                        try {
                            bankService.makeTransaction(transactionId, bankId);
                            System.out.println("Transaction successful.");
                        } catch (InsufficientFundsException | NonExistentAccountException | NonExistentBankException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Transaction failed.");
                        }
                    }
                    break;
                case "3":
                    if (findBankService.findBankById(bankId).get().getAccounts().isEmpty()) {
                        ValidationUtil.bankHasNoAccounts();
                        break;
                    }
                    try {
                        printAccountService.printAllAccountsInBank(bankId);
                    } catch (NonExistentBankException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "4":
                    idFrom = ValidationUtil.getValidAccountId(scanner);
                    try {
                        printAccountService.printAccountDetails(idFrom);
                    } catch (NonExistentAccountException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "5":
                    try {
                        System.out.println(bankPrintingService.printBankDetails(bankId));
                    } catch (NonExistentBankException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "6":
                    if (findBankService.findBankById(bankId).isPresent())
                        System.out.format("Total transaction fee: %10.2f$",
                                findBankService.findBankById(bankId).get().getTotalTransactionFeeAmount());
                    break;
                case "7":
                    if (findBankService.findBankById(bankId).isPresent())
                        System.out.format("Total transfer amount: %10.2f$",
                                findBankService.findBankById(bankId).get().getTotalTransferAmount());
                    break;
                case "8":
                    Bank bank  = findBankService.findBankById(bankId).get();
                    System.out.println("Thank you for using " + bank.getName());
                    return;
                default:
                    System.out.println("Please select a valid option.");
                    break;
            }
        }

    }
}
