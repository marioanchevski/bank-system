package com.mybanksystem.account.service.Impl;

import com.mybanksystem.account.JpaAccountRepository;
import com.mybanksystem.account.model.Account;
import com.mybanksystem.account.model.dto.DetailedAccountDTO;
import com.mybanksystem.account.model.exceptions.NonExistentAccountException;
import com.mybanksystem.account.service.AccountDetailsService;
import com.mybanksystem.account.service.FindTransactionsForAccountService;
import com.mybanksystem.bank.model.exceptions.NonExistentBankException;
import com.mybanksystem.transaction.model.dto.TransactionDisplayDTO;
import com.mybanksystem.transaction.model.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountDetailsServiceImpl implements AccountDetailsService {
    private final FindTransactionsForAccountService findTransactionsForAccountService;
    private final JpaAccountRepository accountRepository;

    @Override
    public DetailedAccountDTO getAccountDetails(String accountUUID) {
        var account = accountRepository.findAccountByUUID(accountUUID)
                .orElseThrow(() -> new NonExistentAccountException(accountUUID));

        var transactions = findTransactionsForAccountService.findAllTransactionsForAccount(accountUUID)
                .stream()
                .map(t-> mapTransactionToTransactionDisplayDTO(t))
                .collect(Collectors.toList());

        return mapAccountToDetailedAccountDTO(account, transactions);
    }

    private TransactionDisplayDTO mapTransactionToTransactionDisplayDTO(Transaction transaction){
        var dto = new TransactionDisplayDTO();
        dto.setBankUUID(transaction.getAccountFrom().getBank().getUUID());
        dto.setAccountFrom(transaction.getAccountFrom().getName());
        dto.setAccountTo(transaction.getAccountTo().getName());
        dto.setAmount(transaction.getAmount().doubleValue());
        dto.setTransactionType(transaction.getType());
        return dto;
    }

    private DetailedAccountDTO mapAccountToDetailedAccountDTO(Account account, List<TransactionDisplayDTO> transactions) {
        var dto = new DetailedAccountDTO();
        dto.setAccountOwner(account.getName());
        dto.setBank(account.getBank().getName());
        dto.setBalance(account.getBalance().doubleValue());
        dto.setTransactions(transactions);
        return dto;
    }
}
