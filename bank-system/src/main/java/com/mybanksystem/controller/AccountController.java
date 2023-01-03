package com.mybanksystem.controller;


import com.mybanksystem.account.model.dto.AccountCreationDTO;
import com.mybanksystem.account.model.dto.AccountDTO;
import com.mybanksystem.account.model.dto.DetailedAccountDTO;
import com.mybanksystem.account.service.AccountDetailsService;
import com.mybanksystem.account.service.CreateAccountService;
import com.mybanksystem.account.service.FindAllAccountsService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final CreateAccountService createAccountService;
    private final FindAllAccountsService findAllAccountsService;
    private final AccountDetailsService accountDetailsService;

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody @Valid AccountCreationDTO account) {
        String response = createAccountService.addAccountToBank(
                account.getAccountOwner(),
                BigDecimal.valueOf(account.getBalance()),
                account.getBank());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<AccountDTO> displayAccounts() {
        return findAllAccountsService.findAllAccounts();
    }

    @GetMapping("/details/{accountUUID}")
    public DetailedAccountDTO findAccount(@PathVariable String accountUUID) {
        return accountDetailsService.getAccountDetails(accountUUID);
    }


}
