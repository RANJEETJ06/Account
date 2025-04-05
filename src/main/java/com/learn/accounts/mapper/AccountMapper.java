package com.learn.accounts.mapper;

import com.learn.accounts.DTO.AccountDto;
import com.learn.accounts.entities.Accounts;

public class AccountMapper {
    public static AccountDto AccountToDto(Accounts accounts,AccountDto accountDto){
        accountDto.setAccountNumber(accounts.getAccountNumber());
        accountDto.setBranchAddress(accounts.getBranchAddress());
        accountDto.setAccountType(accounts.getAccountType());
        return accountDto;
    }
    public static Accounts DtoToAccounts(AccountDto accountDto,Accounts account){
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setBranchAddress(accountDto.getBranchAddress());
        account.setAccountType(accountDto.getAccountType());
        return account;
    }
}
