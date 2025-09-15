package com.example.controller;

import com.example.dto.CustomerResponseDto;
import com.example.dto.CustomerSignUpDto;
import com.example.service.CustomerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Post("/signup")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<CustomerResponseDto> signUp(@Body CustomerSignUpDto customerSignUpDto){
       return HttpResponse.created(customerService.createCustomer(customerSignUpDto).toDTO());
    }

    @Get("/{id}")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public  CustomerResponseDto findCustomerById(Long id){
        return customerService.findCustomerById(id);
    }


    @Delete("/{id}")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public void deleteCustomerById(Long id){
        customerService.deleteCustomer(id);
    }


}
