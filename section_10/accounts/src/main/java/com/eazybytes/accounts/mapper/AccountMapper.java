package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.AccountDto;
import com.eazybytes.accounts.entity.Account;

public class AccountMapper {

    //Entity to DTO Mapper
    public static AccountDto mapToAccountDto(Account account, AccountDto accountDto){
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBranchAddress(account.getBranchAddress());
    return accountDto;
    }

    //DTO to entity mapper
    public static Account mapToAccount(AccountDto accountDto, Account account){
        account.setAccountType(accountDto.getAccountType());
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setBranchAddress(accountDto.getBranchAddress());
        return account;
    }

}
