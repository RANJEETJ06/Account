package com.learn.accounts.services;

import com.learn.accounts.DTO.CustomerDto;

public interface IAccountService {

    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
    boolean updateCommunicationStatus(Long accountNumber);
}
