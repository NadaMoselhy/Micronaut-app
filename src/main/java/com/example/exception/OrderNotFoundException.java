package com.example.exception;

import lombok.Getter;

@Getter
public class OrderNotFoundException extends RuntimeException{
    Long id;
    public OrderNotFoundException(Long id){
        super("order does not exist with this id : "+ id);
        this.id = id;
    }
}

