package com.example.controller;

import com.example.dto.CustomerResponseDto;
import com.example.dto.CustomerSignUpDto;
import com.example.dto.CustomerUpdateDto;
import com.example.entity.Customer;
import com.example.service.CustomerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Controller("/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Post("/signup")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<CustomerResponseDto> signUp(@Body CustomerSignUpDto customerSignUpDto) {
        return HttpResponse.created(customerService.createCustomer(customerSignUpDto).toDTO());
    }

    @Get("/{id}")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public CustomerResponseDto findCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @Get()
    @Secured(SecurityRule.IS_ANONYMOUS)
    public ArrayList<CustomerResponseDto> findAll() {

        return customerService.getAllCustomers().stream().map(Customer::toDTO).collect(Collectors.toCollection(ArrayList::new));

    }


    @Delete("/{id}")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public void deleteCustomerById(Long id) {
        customerService.deleteCustomer(id);
    }


    @Patch("/{id}")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<CustomerResponseDto> updateCustomer(@PathVariable Long id,
                                                            @Body CustomerUpdateDto customerUpdateDto) {
        return HttpResponse.ok(customerService.updateCustomer(id, customerUpdateDto).toDTO());

    }



}
