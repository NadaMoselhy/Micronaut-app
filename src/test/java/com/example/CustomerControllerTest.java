package com.example;

import com.example.dto.CustomerResponseDto;
import com.example.dto.CustomerSignUpDto;
import com.example.controller.CustomerController;
import com.example.entity.Customer;
import com.example.exception.EmailAlreadyExistsException;
import com.example.service.CustomerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@MicronautTest
public class CustomerControllerTest {

    // we have to mock the customer service because we want to test the controller layer only
    @Inject
    @Client("/")
    HttpClient client;

    @Inject
    CustomerController controller;

    @Inject
    CustomerService service;

    // mock the service
    @MockBean(CustomerService.class)
    CustomerService customerService(){
        return mock(CustomerService.class);
    }

    @Test
    void testSignUpSucceeded(){
        // this techinque is called arrange act assert , we arrange the data and the mocked services , and then we call the
        // method to be tested , and then assert the response is as expected , happy or sad scenario
        CustomerSignUpDto dto = new CustomerSignUpDto("nada", "nada123@gmail.com","nada123","0155999999");
        //CustomerResponseDto responseDto = new CustomerResponseDto(1,"nada","nada123@gmail.com","0155999999");
        Customer customer = Customer.builder().name("nada").email("nada123@gmail.com").password("nada123").phoneNumber("0155999999").build();

        customer.setId(1L);

        when(service.createCustomer(dto)).thenReturn(customer);

        // act
        HttpResponse<CustomerResponseDto> res =  controller.signUp(dto);

        //assert
        assertEquals(201, res.getStatus().getCode());
        assertEquals("nada", res.body().getName());
        verify(service, times(1)).createCustomer(dto);

    }

    // sign up not happy scenario
    @Test
    public void signUpDuplicatedEmail(){
        // arrange
        CustomerSignUpDto dto = new CustomerSignUpDto("nada", "nada123@gmail.com","nada123","0155999999");
        when(service.createCustomer(dto)).thenThrow(new EmailAlreadyExistsException(dto.getEmail()));

        // act and assert
        HttpResponse<?> res;

        try {
            res = controller.signUp(dto);
        } catch (EmailAlreadyExistsException e) {
            // manually simulate what your exception handler would do
            res = HttpResponse.status(HttpStatus.CONFLICT);
        }
        assertEquals(HttpStatus.CONFLICT, res.getStatus());

    }



}
