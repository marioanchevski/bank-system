package com.mybanksystem.transaction.service.Impl;

import com.mybanksystem.transaction.Transaction;
import com.mybanksystem.transaction.TransactionRepository;
import com.mybanksystem.transaction.TransactionType;
import com.mybanksystem.transaction.service.TransactionPrintingService;

public class TransactionPrintingServiceImpl implements TransactionPrintingService {
    private final TransactionRepository transactionRepository;

    public TransactionPrintingServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public String printTransaction(String transactionId) {
        Transaction transaction = transactionRepository.findTransactionById(transactionId);
        if (transaction.getType().equals(TransactionType.NORMAL))
            return String.format("Sent:%.2f$, from:%d to:%d, account:%d paid: %s\n",
                    transaction.getAmount(),
                    transaction.getAccountFrom().getId(),
                    transaction.getAccountTo().getId(),
                    transaction.getAccountFrom().getId(),
                    transaction.getDescription());
        else
            return String.format("%s: %.2f$, account:%d, paid %s\n",
                    transaction.getType(),
                    transaction.getAmount(),
                    transaction.getAccountFrom().getId(),
                    transaction.getDescription());
    }
}
