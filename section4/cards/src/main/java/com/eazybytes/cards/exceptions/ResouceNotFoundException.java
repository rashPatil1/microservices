package com.eazybytes.cards.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResouceNotFoundException extends RuntimeException {

    public ResouceNotFoundException(String resource, String fieldName, String value) {
        super(String.format("%s not found with %s : '%s'", resource, fieldName, value));
    }
}
