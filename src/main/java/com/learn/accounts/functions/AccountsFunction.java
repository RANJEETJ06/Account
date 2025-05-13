package com.learn.accounts.functions;

import com.learn.accounts.services.IAccountService;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunction {
    @Bean
    public Consumer<Long> updateCommunication(IAccountService accountService) {
        return accNo -> {
            LoggerFactory.getLogger(AccountsFunction.class).info("Received message to update communication status for account: {}", accNo);
            boolean result = accountService.updateCommunicationStatus(accNo);
            LoggerFactory.getLogger(AccountsFunction.class).info("Update result: {}", result);
        };
    }

}
