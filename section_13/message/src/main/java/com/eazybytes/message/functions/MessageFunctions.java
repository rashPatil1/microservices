package com.eazybytes.message.functions;

import com.eazybytes.message.dto.AccountsMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {
    private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    //When Are These Functions Called?
    //Binding to Input Channels:
    //Spring Cloud Stream automatically binds these functions to input channels defined in the application.yml configuration.

    @Bean
    public Function<AccountsMsgDto, AccountsMsgDto> email() {
        return accountsMsgDto -> {
            log.info("Sending email with details received: "  +accountsMsgDto.toString());
            //Assume here we are sending email
            //The email function returns a lambda expression that implements the apply method of the Function interface
            //The lambda accepts a single parameter, accountsMsgDto, of type AccountsMsgDto.
            //It performs some operations and returns the same accountsMsgDto as out
             return accountsMsgDto;
             //Buiness logic - 	Accounts mricorservice sends message to message broker, message broker sends message to email service
            // The email function provides output (AccountsMessageDto) that acts as input for the sms function.
            //Need to compose both functions
        };
    }


    @Bean
    public Function<AccountsMsgDto, Long> sms() {
        return accountsMsgDto -> {
            log.info("Sending sms with details received: "  +accountsMsgDto.toString());
            //Assume here we are sending sms
            //The sms function returns a lambda expression that implements the apply method of the Function interface
              return accountsMsgDto.accountNumber();
              //The lambda accepts a single parameter, accountsMsgDto, of type AccountsMsgDto.
            //returns the account number
        };
    }
}
