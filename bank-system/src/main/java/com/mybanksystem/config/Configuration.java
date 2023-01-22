package com.mybanksystem.config;

import com.mybanksystem.bank.model.BankConfigurationGroupType;
import com.mybanksystem.bank.model.entity.BankConfiguration;
import com.mybanksystem.bank.service.BankConfigurationFetchService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    RestTemplate restTemplate(){
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return new RestTemplate();
    }
    @Bean
    CommandLineRunner commandLineRunner(BankConfigurationFetchService fetchService) {
        return args -> fetchService.getConfigurations();
    }

    @Bean
    public Map<BankConfigurationGroupType, BankConfiguration> bankConfigurationsMap() {
        return new HashMap<BankConfigurationGroupType, BankConfiguration>();
    }
}
