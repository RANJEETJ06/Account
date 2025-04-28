package com.learn.accounts.services.impl;

import com.learn.accounts.DTO.AccountDto;
import com.learn.accounts.DTO.CustomerDetailsDto;
import com.learn.accounts.Exception.ResourceNotFoundException;
import com.learn.accounts.entities.Accounts;
import com.learn.accounts.entities.Customer;
import com.learn.accounts.mapper.AccountMapper;
import com.learn.accounts.mapper.CustomerMapper;
import com.learn.accounts.repository.AccountRepository;
import com.learn.accounts.repository.CustomerRepository;
import com.learn.accounts.services.ICustomerService;
import com.learn.accounts.services.clients.CardsFeignClient;
import com.learn.accounts.services.clients.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomer(String mobileNumber,String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDetailsDto customerDetailsDto= CustomerMapper.mapToCustomerDetailsDto(customer,new CustomerDetailsDto());
        customerDetailsDto.setAccountDto(AccountMapper.mapToAccountsDto(accounts,new AccountDto()));
        customerDetailsDto.setCardsDto(cardsFeignClient.fetchCardDetails(correlationId,mobileNumber).getBody());
        customerDetailsDto.setLoansDto(loansFeignClient.fetchLoanDetails(correlationId,mobileNumber).getBody());
        return customerDetailsDto;
    }
}
