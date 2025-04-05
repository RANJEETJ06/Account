package com.learn.accounts.services;

import com.learn.accounts.DTO.CustomerDto;

public interface IAccountService {

    void createAccount(CustomerDto customerDto);
    CustomerDto fetchCustomerByMobileNumber(String MobileNumber);
    Boolean updateAccount(CustomerDto customerDto);
    Boolean deleteAccount(Long accountNumber);
}
