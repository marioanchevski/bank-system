package com.mybanksystem.bank.service.Impl;

import com.mybanksystem.bank.model.BankConfigurationGroupType;
import com.mybanksystem.bank.model.dto.BankConfigurationFetch;
import com.mybanksystem.bank.model.entity.BankConfiguration;
import com.mybanksystem.bank.service.BankConfigurationFetchService;
import com.mybanksystem.exception_handlers.RestTemplateResponseErrorHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankConfigurationFetchServiceImpl implements BankConfigurationFetchService {
    private final Map<BankConfigurationGroupType, BankConfiguration> bankConfigurationsMap;
    private final RestTemplate restTemplate;

    @Value("${bank.configuration.url}")
    private String configuration_url;
    @Value("${bank.configuration.password}")
    private String password;
    @Value("${bank.configuration.username}")
    private String username;

    @Override
    public void getConfigurations() {
        ResponseEntity<Collection<BankConfigurationFetch>> result = null;
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

        try {
            result = restTemplate.exchange(configuration_url, HttpMethod.GET, new HttpEntity<String>(encodeCredentials()), new ParameterizedTypeReference<Collection<BankConfigurationFetch>>() {
            });
            Objects.requireNonNull(result.getBody())
                    .forEach(cfg -> bankConfigurationsMap.put(cfg.getGroupation(), mapBankConfigurationFetchToBankConfiguration(cfg)));
            log.info("Connection successful, data fetched.");
        } catch (ResourceAccessException ex) {
            log.error(String.format("Could not connect to service.%n%s", ex.getMessage()));
        }

    }

    private BankConfiguration mapBankConfigurationFetchToBankConfiguration(BankConfigurationFetch data) {
        var bankConfiguration = new BankConfiguration();
        bankConfiguration.setPercentFeeAmount(data.getPercentFeeAmount());
        bankConfiguration.setThresholdAmount(data.getThresholdAmount());
        bankConfiguration.setFlatFeeAmount(data.getFlatFeeAmount());
        return bankConfiguration;
    }
    // TODO resttemplate builder
    private HttpHeaders encodeCredentials() {
        String credentials = String.format("%s:%s", username, password);
        byte[] plainCredsBytes = credentials.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        return headers;
    }

}
