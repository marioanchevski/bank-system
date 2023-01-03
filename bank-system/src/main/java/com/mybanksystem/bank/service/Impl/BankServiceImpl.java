package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.account.model.exceptions.ZeroAmountException;
import com.mybanksystem.bank.repository.JpaBankTransferDetailsRepository;
import com.mybanksystem.bank.service.BankService;
import com.mybanksystem.account.model.exceptions.InsufficientFundsException;
import com.mybanksystem.transaction.JpaTransactionRepository;
import com.mybanksystem.transaction.model.enumeration.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
    private final JpaBankTransferDetailsRepository bankTransferDetailsRepository;
    private final JpaTransactionRepository transactionRepository;

    @Override
    @Transactional
    public void checkValidAmount(String transactionUUID) {
        var transaction = transactionRepository.findTransactionByUUID(transactionUUID);

        var fromAccount = transaction.getAccountFrom();
        var totalTransferAmount = transaction.getAmount().add(transaction.getProvision());

        if (transaction.getAmount().equals(BigDecimal.valueOf(0.0))) {
            throw new ZeroAmountException();
        }

        if (transaction.getType().equals(TransactionType.DEPOSIT)) {

            if (fromAccount.getBalance().add(transaction.getAmount())
                    .compareTo(transaction.getProvision()) < 0) {
                throw new InsufficientFundsException(transaction.getAmount().doubleValue());
            }
        } else if (transaction.getType().equals(TransactionType.WITHDRAW)) {
            if (fromAccount.getBalance().compareTo(totalTransferAmount) < 0) {
                throw new InsufficientFundsException(fromAccount.getBalance().doubleValue()
                        , transaction.getAmount().doubleValue());
            }
        } else {
            if (fromAccount.getBalance().compareTo(totalTransferAmount) < 0) {
                throw new InsufficientFundsException(fromAccount.getBalance().doubleValue()
                        , transaction.getAmount().doubleValue());
            }
        }

        var bankTransferDetails = bankTransferDetailsRepository.findByBankUUID(fromAccount.getBank().getUUID());
        bankTransferDetails.setTotalTransferAmount(bankTransferDetails.getTotalTransferAmount().add(transaction.getAmount()));
        bankTransferDetails.setTotalTransactionFeeAmount(bankTransferDetails.getTotalTransactionFeeAmount().add(transaction.getProvision()));

    }


}
