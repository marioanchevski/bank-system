package com.mybanksystem.controller;

import com.mybanksystem.account.model.dto.AccountDTO;
import com.mybanksystem.bank.model.*;
import com.mybanksystem.bank.model.dto.*;
import com.mybanksystem.bank.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/banks")

public class BankController {
    private final FindAllBanksService findAllBanksService;
    private final FindBankService findBankService;
    private final CreateBankService createBankService;
    private final SetConfigurationForBankService setConfigurationForBankService;
    private final UpdateBankConfigurationService updateBankConfigurationService;
    private final FindAllAccountsInBankService findAllAccountsInBankService;

    @PostMapping
    public ResponseEntity<String> createBank(@RequestBody @Valid BankCreationDTO newBank) {
        return new ResponseEntity<>(createBankService.createNewBank(newBank.getBankName()), HttpStatus.CREATED);
    }

    @PostMapping("{bankUUID}/configuration")
    public BankDTO setBankConfiguration(@PathVariable String bankUUID,
                                        @RequestBody BankConfigurationCreationDTO bankConfiguration) {
        return setConfigurationForBankService.createAndSetBankConfiguration(
                bankUUID,
                BankConfigurationGroupType.valueOf(bankConfiguration.getBankGroup()));
    }

    @PutMapping("{bankUUID}/configuration")
    public BankConfigurationDTO updateBankConfiguration(@PathVariable String bankUUID,
                                                        @RequestBody BankConfigurationUpdateDTO updateConfig) {
        return updateBankConfigurationService.updateBankConfigurationForBank(
                bankUUID,
                updateConfig.getThresholdAmount(),
                updateConfig.getFlatFeeAmount()
                , updateConfig.getPercentFeeAmount());
    }

    @GetMapping("/{bankUUID}/accounts")
    public Collection<AccountDTO> displayAccountsInBank(@PathVariable String bankUUID) {
        return findAllAccountsInBankService.findAll(bankUUID);
    }

    @GetMapping
    public List<BankDTO> listBanks() {
        return findAllBanksService.findAllBanks();
    }

    @GetMapping("/{bankUUID}")
    public BankDTO getBank(@PathVariable String bankUUID) {
        return this.findBankService.findBankById(bankUUID);
    }


}


