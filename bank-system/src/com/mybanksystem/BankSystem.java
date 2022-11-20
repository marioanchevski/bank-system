package com.mybanksystem;

import com.mybanksystem.account.AccountRepository;
import com.mybanksystem.account.service.AccountService;
import com.mybanksystem.account.service.CreateAccountService;
import com.mybanksystem.account.service.FindAccountService;
import com.mybanksystem.account.service.Impl.*;
import com.mybanksystem.account.service.AccountPrintingService;
import com.mybanksystem.bank.BankDTO;
import com.mybanksystem.bank.BankRepository;
import com.mybanksystem.bank.service.*;
import com.mybanksystem.bank.service.Impl.*;
import com.mybanksystem.account.exceptions.InsufficientFundsException;
import com.mybanksystem.account.exceptions.NonExistentAccountException;
import com.mybanksystem.bank.exceptions.NonExistentBankException;
import com.mybanksystem.bootstrap.DataHolder;
import com.mybanksystem.transaction.*;
import com.mybanksystem.transaction.service.Impl.TransactionPrintingServiceImpl;
import com.mybanksystem.transaction.service.TransactionPrintingService;
import com.mybanksystem.transaction.service.TransactionService;
import com.mybanksystem.transaction.service.Impl.TransactionServiceImpl;
import com.mybanksystem.util.ValidationUtil;

import java.util.List;
import java.util.Scanner;

public class BankSystem {

    public static void main(String[] args) {
        Long bankId = 100L;
        Scanner scanner = new Scanner(System.in);
        AccountRepository accountRepository = new AccountRepository();
        TransactionRepository transactionRepository = new TransactionRepository();
        BankRepository bankRepository = new BankRepository();
        TransactionPrintingService transactionPrintingService = new TransactionPrintingServiceImpl(transactionRepository);
        FindTransactionsForAccountServiceImpl findTransactionsForAccountService = new FindTransactionsForAccountServiceImpl(transactionRepository);

        DataHolder dataHolder = new DataHolder(bankRepository, accountRepository);
        dataHolder.init();

        FindAccountService findAccountService = new FindAccountServiceImpl(accountRepository);
        AccountPrintingService printAccountService = new AccountPrintingServiceImpl(bankRepository, findAccountService, transactionPrintingService, findTransactionsForAccountService);
        AccountService accountService = new AccountServiceImpl(transactionRepository);

        FindBankService findBankService = new FindBankServiceImpl(bankRepository);
        BankPrintingService bankPrintingService = new BankPrintingServiceImpl(findBankService);
        BankService bankService = new BankServiceImpl(transactionRepository, findBankService, accountService, findAccountService);
        CreateAccountService createAccountService = new CreateAccountServiceImpl(findBankService, accountRepository);
        CreateBankService createBankService = new CreateBankServiceImpl(bankRepository);
        MapperService mapperService = new MapperServiceImpl();
        FindAllBanksService findAllBanksService = new FidAllBanksServiceImpl(bankRepository, mapperService);

        TransactionService transactionService = new TransactionServiceImpl(transactionRepository, findBankService, findAccountService);


        while (true) {
            ValidationUtil.showMenu();
            String decision = scanner.nextLine();
            switch (decision) {
                case "1":
                    String bankName = ValidationUtil.getValidName(scanner, ValidationUtil.BANK_NAME_MSG);
                    double flatFee = ValidationUtil.getValidAmountInput(scanner, ValidationUtil.FLAT_AMOUNT_MSG);
                    int percentFee = ValidationUtil.getValidFlatPercent(scanner);
                    double thresholdAmount = ValidationUtil.getValidAmountInput(scanner, ValidationUtil.FLAT_PERCENT_MSG);
                    createBankService.createNewBank(bankName, thresholdAmount, flatFee, percentFee);
                    System.out.println(bankName + " successfully created.");
                    break;
                case "2":
                    List<BankDTO> bankDTOS = findAllBanksService.findAllBanks();
                    bankDTOS.forEach(System.out::println);
                    break;
                case "3":
                    bankDTOS = findAllBanksService.findAllBanks();
                    bankDTOS.forEach(System.out::println);
                    bankId = ValidationUtil.getValidBankId(scanner);
                    try {
                        findBankService.findBankById(bankId);
                        String name = ValidationUtil.getValidName(scanner, ValidationUtil.ACCOUNT_NAME_MSG);
                        double balance = ValidationUtil.getValidAmountInput(scanner, ValidationUtil.AMOUNT_BALANCE_MSG);
                        createAccountService.addAccountToBank(name, balance, bankId);
                    } catch (NonExistentBankException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "4":
                  /*  try {
                        if (findBankService.findBankById(bankId).getAccounts().isEmpty()) {
                            ValidationUtil.bankHasNoAccounts();
                            break;
                        }
                    } catch (NonExistentBankException e) {
                        throw new RuntimeException(e);
                    }*/


                    bankDTOS = findAllBanksService.findAllBanks();
                    bankDTOS.forEach(System.out::println);
                    bankId = ValidationUtil.getValidBankId(scanner);
                    try {
                        findBankService.findBankById(bankId);
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
                            } catch (InsufficientFundsException | NonExistentAccountException |
                                     NonExistentBankException e) {
                                System.out.println(e.getMessage());
                                System.out.println("Transaction failed.");
                            }
                        }
                    } catch (NonExistentBankException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "5":

/*                    if (findBankService.findBankById(bankId).get().getAccounts().isEmpty()) {
                        ValidationUtil.bankHasNoAccounts();
                        break;
                    }
*/
                    bankDTOS = findAllBanksService.findAllBanks();
                    bankDTOS.forEach(System.out::println);
                    bankId = ValidationUtil.getValidBankId(scanner);
                    try {
                        printAccountService.printAllAccountsInBank(bankId);
                    } catch (NonExistentBankException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "6":
                    long idFrom = ValidationUtil.getValidAccountId(scanner);
                    try {
                        printAccountService.printAccountDetails(idFrom);
                    } catch (NonExistentAccountException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "7":
                    bankDTOS = findAllBanksService.findAllBanks();
                    bankDTOS.forEach(System.out::println);
                    bankId = ValidationUtil.getValidBankId(scanner);
                    try {
                        System.out.println(bankPrintingService.printBankDetails(bankId));
                    } catch (NonExistentBankException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "8":
                    bankDTOS = findAllBanksService.findAllBanks();
                    bankDTOS.forEach(System.out::println);
                    bankId = ValidationUtil.getValidBankId(scanner);
                    try {
                        System.out.format("Total transaction fee: %10.2f$",
                                findBankService.findBankById(bankId).getTotalTransactionFeeAmount());
                    } catch (NonExistentBankException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "9":
                    bankDTOS = findAllBanksService.findAllBanks();
                    bankDTOS.forEach(System.out::println);
                    bankId = ValidationUtil.getValidBankId(scanner);
                    try {
                        System.out.format("Total transfer amount: %10.2f$",
                                findBankService.findBankById(bankId).getTotalTransferAmount());
                    } catch (NonExistentBankException e) {
                        System.out.println(e.getMessage());
                    }
                case "0":
                    System.out.println("Thank you for using our services.");
                    return;
                default:
                    System.out.println("Please select a valid option.");
                    break;
            }
        }

    }
}
