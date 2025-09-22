package com.example.exception;

import lombok.Getter;

@Getter
public class InvalidCategoryException extends RuntimeException{
    String category;
    public InvalidCategoryException(String category){
        super("this category does not exist : " + category);
        this.category = category;
    }
}
