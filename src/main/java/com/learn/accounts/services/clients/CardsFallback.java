package com.learn.accounts.services.clients;

import com.learn.accounts.DTO.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient {
    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
        return ResponseEntity.ok().body(null);
    }
}
