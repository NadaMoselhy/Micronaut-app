package com.example.exception;

import lombok.Getter;

@Getter
public class CustomerNotFoundException extends RuntimeException{
    private final Long id;
    public CustomerNotFoundException(Long id){
        super("Customer does not exist with this id : " + id);
        this.id = id;
    }
}
