package com.learn.accounts.services.clients;

import com.learn.accounts.DTO.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards",fallback = CardsFallback.class)
public interface CardsFeignClient {
    @GetMapping(value = "api/fetch",consumes = "application/json")
    ResponseEntity<CardsDto> fetchCardDetails(@RequestHeader("learn-correlation-id")String correlationId, @RequestParam String mobileNumber);
}
