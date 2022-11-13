package com.mybanksystem;

import com.mybanksystem.account.Account;
import com.mybanksystem.account.AccountService;
import com.mybanksystem.account.AccountServiceImpl;
import com.mybanksystem.bank.Bank;
import com.mybanksystem.bank.BankService;
import com.mybanksystem.bank.BankServiceImpl;
import com.mybanksystem.account.exceptions.InsufficientFundsException;
import com.mybanksystem.account.exceptions.NonExistentAccountException;
import com.mybanksystem.transaction.Transaction;
import com.mybanksystem.transaction.TransactionService;
import com.mybanksystem.transaction.TransactionServiceImpl;
import com.mybanksystem.transaction.TransactionType;
import com.mybanksystem.util.ValidationUtil;

import java.util.Scanner;

public class BankSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountService accountService = new AccountServiceImpl();
        BankService bankService = new BankServiceImpl(accountService);
        TransactionService transactionService = new TransactionServiceImpl();

        Bank bank = new Bank("LinkPlus-Bank", 10.00, 2, 10000.00);
        bankService.addAccountToBank(new Account("Mario", 5.00), bank);
        bankService.addAccountToBank(new Account("Vojo", 1000.00), bank);
        bankService.addAccountToBank(new Account("Filip and Ata", 5000.50), bank);

        //com.mybanksystem.bank.Bank bank = init(scanner);

        System.out.println(bankService.printBank(bank));

        while (true) {
            ValidationUtil.showMenu();
            String decision = scanner.nextLine();
            switch (decision) {
                case "1":
                    String name = ValidationUtil.getValidName(scanner);
                    double balance = ValidationUtil.getValidAmountInput(scanner, ValidationUtil.AMOUNT_BALANCE_MSG);
                    bankService.addAccountToBank(new Account(name, balance), bank);
                    break;
                case "2":

                    if (!bankService.hasAccounts(bank)) {
                        ValidationUtil.bankHasNoAccounts(bank);
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
                        Transaction t1 = transactionService.getTransactionInstance(transactionType, idFrom, idTo, amount, bank);
                        try {
                            bankService.makeTransaction(t1, bank);
                            System.out.println("Transaction successful.");
                        } catch (InsufficientFundsException | NonExistentAccountException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Transaction failed.");
                        }
                    }
                    break;
                case "3":
                    if (!bankService.hasAccounts(bank)) {
                        ValidationUtil.bankHasNoAccounts(bank);
                        break;
                    }
                    accountService.printAccounts(bank);
                    break;
                case "4":
                    idFrom = ValidationUtil.getValidAccountId(scanner);
                    try {
                        accountService.getAccountDetails(idFrom, bank);
                    } catch (NonExistentAccountException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "5":
                    System.out.println(bankService.printBank(bank));
                    break;
                case "6":
                    System.out.println(bankService.getTotalTransactionFeeAmount(bank));
                    break;
                case "7":
                    System.out.println(bankService.getTotalTotalTransferAmount(bank));
                    break;
                case "8":
                    System.out.println("Thank you for using " + bank.getName());
                    return;
                default:
                    System.out.println("Please select a valid option.");
                    break;
            }
        }

    }
}
