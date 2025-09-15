package com.example.service;

import com.example.DTO.CustomerResponseDto;
import com.example.DTO.CustomerSignUpDto;
import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import com.example.utils.BCryptPasswordEncoder;
import io.micronaut.http.server.exceptions.NotFoundException;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;


import java.util.Optional;

@Singleton
// the service should be a singleton so it gets injected in the controller as a bean and also so that micronaut
// only creates a single instance for the whole app
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public CustomerService(CustomerRepository customerRepository, BCryptPasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomerResponseDto findCustomerById(Long id){
        Optional<Customer> result = customerRepository.findById(id);

        if(result.isPresent()){
            Customer foundCustomer = result.get();
            return foundCustomer.toDTO();
        }
        else{
            // search for the exception part (akeed hatb2a try and catch)
            throw new NotFoundException();
        }

    }

    @Transactional
    public CustomerResponseDto createCustomer(CustomerSignUpDto customerDto){
        String hashedPassword = passwordEncoder.encode(customerDto.getPassword());
        Customer newCustomer = new Customer().fromDto(customerDto);
        newCustomer.setPassword(hashedPassword);
        Customer returnedCustomer = customerRepository.save(newCustomer);

        return returnedCustomer.toDTO();
    }

    public void deleteCustomer(Long id){
        boolean exists = customerRepository.existsById(id);
        if(exists){
            customerRepository.deleteById(id);
        }

    }

}
