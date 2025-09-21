package com.example.controller;

import com.example.model.dto.CustomerResponseDto;
import com.example.model.dto.CustomerSignUpDto;
import com.example.model.dto.CustomerUpdateDto;
import com.example.model.entity.Customer;
import com.example.model.mapper.CustomerMapper;
import com.example.service.CustomerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.data.model.Page;

@Controller("/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper mapper;

    public CustomerController(CustomerService customerService, CustomerMapper mapper) {
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @Post("/signup")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<CustomerResponseDto> signUp(@Body CustomerSignUpDto customerSignUpDto) {
        return HttpResponse.created(mapper.toDto(customerService.createCustomer(customerSignUpDto)));
    }

    @Get("/{id}")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public CustomerResponseDto findCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @Get()
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<Page<CustomerResponseDto>> findAll(
            @QueryValue(defaultValue = "0") int page,
            @QueryValue(defaultValue = "10") int size
    ) {
        Page<Customer> customers = customerService.getAllCustomers(page,size);
        return HttpResponse.ok(customers.map(c -> mapper.toDto(c)));

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
        return HttpResponse.ok(mapper.toDto(customerService.updateCustomer(id, customerUpdateDto)));

    }



}
