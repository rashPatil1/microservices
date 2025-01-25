package com.eazybytes.cards.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CardAlreadyExistException extends RuntimeException{

    public CardAlreadyExistException(String message) {
        super(message);
    }
}
