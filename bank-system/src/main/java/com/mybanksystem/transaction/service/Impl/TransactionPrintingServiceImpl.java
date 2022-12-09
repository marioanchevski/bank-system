package com.mybanksystem.transaction.service.Impl;

import com.mybanksystem.transaction.JpaTransactionRepository;
import com.mybanksystem.transaction.model.entity.Transaction;
import com.mybanksystem.transaction.model.enumeration.TransactionType;
import com.mybanksystem.transaction.service.TransactionPrintingService;
import org.springframework.stereotype.Service;

@Service
public class TransactionPrintingServiceImpl implements TransactionPrintingService {
    private final JpaTransactionRepository transactionRepository;

    public TransactionPrintingServiceImpl(JpaTransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public String printTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).get();
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
