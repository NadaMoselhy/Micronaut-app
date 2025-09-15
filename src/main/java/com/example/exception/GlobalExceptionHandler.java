package com.example.exception;


import io.micronaut.http.*;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

import java.util.Map;

public class GlobalExceptionHandler implements ExceptionHandler<RuntimeException, HttpResponse<?>> {


    @Override
    public HttpResponse<?> handle(HttpRequest request, RuntimeException e) {
        if(e instanceof EmailAlreadyExistsException ex){
            return HttpResponse.status(HttpStatus.CONFLICT).body(
                    Map.of(
                            "error" , "email already exists",
                            "email" , ex.getEmail()
                    )
            );
        }
        return HttpResponse.serverError(
                Map.of("error", e.getMessage())
        );
    }
}
