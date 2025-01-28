package com.eazybytes.accounts.functions;


import com.eazybytes.accounts.service.IAccountService;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountFunctions {

    private static final Logger log  = LoggerFactory.getLogger(AccountFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication(IAccountService iAccountService){
       return accountNumber -> {
           log.info("Updating Communication status for the account number: {}", accountNumber);
           iAccountService.updateCommunicationStatus(accountNumber);
       };
    }

}
