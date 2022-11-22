package com.mybanksystem.transaction.service;

import com.mybanksystem.util.Bean;

public interface TransactionPrintingService extends Bean {
    String printTransaction(String transactionId);
}
