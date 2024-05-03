package com.estudos.restfullwebservices.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message) {
        super(message);
    }
}
