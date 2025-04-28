package com.learn.accounts.services;

import com.learn.accounts.DTO.CustomerDetailsDto;

public interface ICustomerService {
    CustomerDetailsDto fetchCustomer(String mobileNumber,String correlationId);
}
