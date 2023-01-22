package com.mybanksystem.controller;

import com.mybanksystem.bank.service.BankConfigurationFetchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/configurations")
public class BankConfigurationController {
    private final BankConfigurationFetchService fetchService;

    @GetMapping
    public void callBankConfigurationService() {
        fetchService.getConfigurations();
    }
}
