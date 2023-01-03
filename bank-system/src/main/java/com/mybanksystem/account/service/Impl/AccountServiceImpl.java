package com.mybanksystem.account.service.Impl;

import com.mybanksystem.account.model.Account;
import com.mybanksystem.account.service.AccountService;
import com.mybanksystem.transaction.JpaTransactionRepository;
import com.mybanksystem.transaction.model.enumeration.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final JpaTransactionRepository transactionRepository;

    @Override
    @Transactional
    public void updateAccounts(String transactionUUID) {
        var transaction = transactionRepository.findTransactionByUUID(transactionUUID);

        Account accountFrom = transaction.getAccountFrom();
        Account accountTo = transaction.getAccountTo();
        BigDecimal result = null;


        if (transaction.getType().equals(TransactionType.DEPOSIT)) {
            result = accountFrom.getBalance().subtract(
                    transaction.getAmount().multiply(new BigDecimal("-1"))
                            .add(transaction.getProvision())
            );
            accountFrom.setBalance(result);
        } else if (transaction.getType().equals(TransactionType.WITHDRAW)) {
            result = accountFrom.getBalance().subtract(
                    transaction.getAmount().add(transaction.getProvision())
            );
            accountFrom.setBalance(result);
        } else {
            result = accountFrom.getBalance().subtract(
                    transaction.getAmount().add(
                            transaction.getProvision())
            );

            accountFrom.setBalance(result);

            accountTo.setBalance(accountTo.getBalance().add(transaction.getAmount()));
        }
    }


}
