package com.mybanksystem.components;

import com.mybanksystem.account.exceptions.InsufficientFundsException;
import com.mybanksystem.bank.exceptions.NonExistentBankException;
import com.mybanksystem.bank.service.BankService;
import com.mybanksystem.bank.service.FindBankService;
import com.mybanksystem.transaction.TransactionContext;
import com.mybanksystem.transaction.TransactionType;
import com.mybanksystem.transaction.service.TransactionService;
import com.mybanksystem.util.Constants;
import com.mybanksystem.util.ValidationUtil;
import org.springframework.stereotype.Component;

@Component
public class TransactionComponents {
    private static FindBankService findBankService;
    private static TransactionService transactionService;
    private static BankService bankService;

    public TransactionComponents(FindBankService findBankService, TransactionService transactionService, BankService bankService) {
        this.findBankService =findBankService;
        this.transactionService =transactionService;
        this.bankService =bankService;
    }

    public static void makeNewTransaction() {
        BankComponents.displayBanks();

        Long bankId = ValidationUtil.userInputIdNumberInCorrectFormat(Constants.BANK_NAME_MSG);
        try {
            findBankService.findBankById(bankId);
        } catch (NonExistentBankException e) {
            System.out.println(e.getMessage());
        }
        ValidationUtil.showTransactionMenu();
        TransactionType transactionType = ValidationUtil.transactionMenuDecision();
        long idFrom, idTo;
        if (transactionType != null) {
            idFrom = ValidationUtil.userInputIdNumberInCorrectFormat(Constants.ACCOUNT_NAME_MSG);
            idTo = idFrom;
            if (transactionType == TransactionType.NORMAL) {
                idTo = ValidationUtil.userInputIdNumberInCorrectFormat(Constants.ACCOUNT_NAME_MSG);
                if (idFrom == idTo) {
                    transactionType = TransactionType.DEPOSIT;
                }
            }
            double amount = ValidationUtil.userInputAmountInCorrectFormat(Constants.TRANSACTION_AMOUNT_MSG);
            TransactionContext context = new TransactionContext(transactionType, idFrom, idTo, amount, bankId);


            try {
                String transactionId = transactionService.createTransaction(context);
                bankService.makeTransaction(transactionId, bankId);
                System.out.println("Transaction successful.");
            } catch (InsufficientFundsException |
                     NonExistentBankException e) {
                System.out.println(e.getMessage());
                System.out.println("Transaction failed.");
            }
        }
    }
}
