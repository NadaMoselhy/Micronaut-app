package com.example.exception;

import io.micronaut.http.*;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
public class GlobalExceptionHandler implements ExceptionHandler<RuntimeException, HttpResponse<ApiError>> {

    @Override
    public HttpResponse<ApiError> handle(HttpRequest request, RuntimeException e) {
        String path = request.getPath();

        return switch (e) {
            case EmailAlreadyExistsException ex -> HttpResponse
                    .status(HttpStatus.CONFLICT)
                    .body(ApiError.of("EMAIL_ALREADY_EXISTS", "Email already exists", path, ex.getEmail()));

            case CustomerNotFoundException ex -> HttpResponse
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiError.of("CUSTOMER_NOT_FOUND", "Customer not found", path, ex.getId()));

            case ProductNotFoundException ex -> HttpResponse
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiError.of("PRODUCT_NOT_FOUND", "Product not found", path, ex.getId()));

            case OrderNotFoundException ex -> HttpResponse
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiError.of("ORDER_NOT_FOUND", "Order not found", path, ex.getId()));

            case InvalidCategoryException ex -> HttpResponse
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiError.of("INVALID_CATEGORY", "Invalid category", path, ex.getCategory()));

            default -> HttpResponse
                    .serverError(ApiError.of("GENERIC_ERROR", e.getMessage(), path, null));
        };
    }
}
