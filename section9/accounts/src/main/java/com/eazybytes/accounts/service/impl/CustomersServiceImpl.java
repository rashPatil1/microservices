package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.AccountDto;
import com.eazybytes.accounts.dto.CardDto;
import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.dto.LoanDto;
import com.eazybytes.accounts.entity.Account;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exceptions.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.ICustomerService;
import com.eazybytes.accounts.service.client.CardsFeignClient;
import com.eazybytes.accounts.service.client.LaonsFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomerService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LaonsFeignClient laonsFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber));
        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "CustomerId", customer.getCustomerId().toString()));

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());

        AccountDto accountDto = AccountMapper.mapToAccountDto(account, new AccountDto());
        customerDetailsDto.setAccountDto(accountDto);

        ResponseEntity<CardDto> cardDtoResponse= cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        customerDetailsDto.setCardDto(cardDtoResponse.getBody());

        ResponseEntity<LoanDto> loanDtoResponse = laonsFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        customerDetailsDto.setLoanDto(loanDtoResponse.getBody());

        return customerDetailsDto;
    }
}
