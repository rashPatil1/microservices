package com.eazybytes.accounts.service.client;

import com.eazybytes.accounts.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {


    @GetMapping(value = "api/fetch", consumes = "application/json")
    public ResponseEntity<CardDto> fetchCardDetails(@RequestHeader("eazybank-correlation-id") String correlationId,
                                                    @RequestParam  String mobileNumber);


}
