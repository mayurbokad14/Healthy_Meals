package com.wipro.hma.ps.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PartnerNotFoundException extends RuntimeException{
    public PartnerNotFoundException(String message){
        super(message);
    }
}

