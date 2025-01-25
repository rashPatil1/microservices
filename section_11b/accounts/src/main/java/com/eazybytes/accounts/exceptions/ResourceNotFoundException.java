package com.eazybytes.accounts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String rescourceName, String fieldNumber, String value){
        super(String.format("%s not found with given input data %s: '%s'", rescourceName,fieldNumber,value));
    }

}
