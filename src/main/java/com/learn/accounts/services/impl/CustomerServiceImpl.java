package com.learn.accounts.services.impl;

import com.learn.accounts.DTO.AccountDto;
import com.learn.accounts.DTO.CardsDto;
import com.learn.accounts.DTO.CustomerDetailsDto;
import com.learn.accounts.DTO.LoansDto;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    @Qualifier("com.learn.accounts.services.clients.CardsFeignClient")
    private CardsFeignClient cardsFeignClient;
    @Qualifier("com.learn.accounts.services.clients.LoansFeignClient")
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

        ResponseEntity<CardsDto> cardsDtoResponseEntity= cardsFeignClient.fetchCardDetails(correlationId,mobileNumber);
        if(cardsDtoResponseEntity!=null){
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }
        ResponseEntity<LoansDto> loansDtoResponseEntity=loansFeignClient.fetchLoanDetails(correlationId,mobileNumber);
        if(loansDtoResponseEntity!=null){
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }
        return customerDetailsDto;
    }
}
