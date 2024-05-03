package com.estudos.restfullwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserFoundException extends RuntimeException {
    public UserFoundException(String message) {
        super(message);
    }
}
