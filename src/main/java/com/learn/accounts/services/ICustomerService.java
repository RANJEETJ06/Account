package com.learn.accounts.services;

import com.learn.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {
    CustomerDetailsDto fetchCustomer(String mobileNumber,String correlationId);
}
