package com.wu.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException{

    public AlreadyExistsException(){
        super();
    }
    public AlreadyExistsException(String message){
        super(message);
    }
}
