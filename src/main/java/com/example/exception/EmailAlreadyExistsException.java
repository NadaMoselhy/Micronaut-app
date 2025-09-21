package com.example.exception;

import lombok.Getter;

@Getter
public class EmailAlreadyExistsException extends RuntimeException{
    private final String email;
    public EmailAlreadyExistsException(String email){
        super("email already exists: " + email);
        this.email = email;
    }

}
