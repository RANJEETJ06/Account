package com.learn.accounts.services.impl;

import com.learn.accounts.DTO.AccountDto;
import com.learn.accounts.DTO.CustomerDto;
import com.learn.accounts.Exception.CustomerAlreadyExistException;
import com.learn.accounts.Exception.ResourceNotFoundException;
import com.learn.accounts.constants.AccountConstants;
import com.learn.accounts.entities.Accounts;
import com.learn.accounts.entities.Customer;
import com.learn.accounts.mapper.AccountMapper;
import com.learn.accounts.mapper.CustomerMapper;
import com.learn.accounts.repository.AccountRepository;
import com.learn.accounts.repository.CustomerRepository;
import com.learn.accounts.services.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer= CustomerMapper.dtoToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistException("Number Already Exist:"+customer.getMobileNumber());
        }
        Customer savedCustomer= customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchCustomerByMobileNumber(String MobileNumber) {
        Customer customer=this.customerRepository.findByMobileNumber(MobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","Mobile Number",MobileNumber)
        );
        Accounts account= this.accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Account","Customer ID",customer.getCustomerId().toString())
        );
        CustomerDto customerDto= CustomerMapper.customerToDto(customer,new CustomerDto());
        AccountDto accountDto=AccountMapper.AccountToDto(account,new AccountDto());
        customerDto.setAccountDto(accountDto);
        return customerDto;
    }

    @Override
    public Boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdate=false;
        AccountDto accountDto=customerDto.getAccountDto();
        if(accountDto!=null){
            Accounts accounts=this.accountRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    ()->new ResourceNotFoundException("Account","Account Number",accountDto.getAccountNumber().toString())
            );
            Long customerId=accounts.getCustomerId();
            Customer customer=this.customerRepository.findById(customerId).orElseThrow(
                    ()->new ResourceNotFoundException("Customer","Customer Number",customerId.toString())
            );

            Accounts updatedAccount=AccountMapper.DtoToAccounts(accountDto,accounts);

            Customer updatedCustomer=CustomerMapper.dtoToCustomer(customerDto,customer);

            accountRepository.save(updatedAccount);
            customerRepository.save(updatedCustomer);
            isUpdate=true;
        }
        return isUpdate;
    }

    @Override
    public Boolean deleteAccount(Long accountNumber) {
        Accounts accounts=this.accountRepository.findById(accountNumber).orElseThrow(
                ()->new ResourceNotFoundException("Account","Account Number",accountNumber.toString())
        );
        Customer customer=this.customerRepository.findById(accounts.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Customer","Customer ID",accounts.getCustomerId().toString())
        );
        this.accountRepository.delete(accounts);
        this.customerRepository.delete(customer);
        return true;
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);

        return newAccount;
    }
}
