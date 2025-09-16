package com.example.service;

import com.example.dto.CustomerResponseDto;
import com.example.dto.CustomerSignUpDto;
import com.example.dto.CustomerUpdateDto;
import com.example.entity.Customer;
import com.example.exception.CustomerNotFoundException;
import com.example.exception.EmailAlreadyExistsException;
import com.example.repository.CustomerRepository;
import com.example.utils.BCryptPasswordEncoder;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public CustomerResponseDto findCustomerById(Long id) {
        Optional<Customer> result = customerRepository.findById(id);

        if (result.isPresent()) {
            Customer foundCustomer = result.get();
            return foundCustomer.toDTO();
        } else {
            throw new CustomerNotFoundException(id);
        }

    }

    @Transactional
    public Customer createCustomer(CustomerSignUpDto customerDto) {

        boolean exists = customerRepository.existsByEmail(customerDto.getEmail());
        if (exists) {
            throw new EmailAlreadyExistsException(customerDto.getEmail());
        } else {
            String hashedPassword = passwordEncoder.encode(customerDto.getPassword());
            Customer newCustomer = new Customer().fromDto(customerDto);
            newCustomer.setPassword(hashedPassword);


            return customerRepository.save(newCustomer);
        }
    }

    public void deleteCustomer(Long id) {
        boolean exists = customerRepository.existsById(id);
        if (exists) {
            customerRepository.deleteById(id);
        }

    }

    @Transactional
    public Customer updateCustomer(long id, CustomerUpdateDto customerUpdateDto) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            // update the email
            String newEmail = customerUpdateDto.getEmail();
            Customer existingCustomer = customer.get();
            if (newEmail != null && !newEmail.equals(existingCustomer.getEmail())) {
                if (customerRepository.existsByEmail(newEmail)) {
                    throw new EmailAlreadyExistsException(newEmail);
                }
                existingCustomer.setEmail(newEmail);
            }
            if (customerUpdateDto.getName() != null) {
                existingCustomer.setName(customerUpdateDto.getName());
            }
            if (customerUpdateDto.getPhoneNumber() != null) {
                existingCustomer.setPhoneNumber(customerUpdateDto.getPhoneNumber());
            }
            if (customerUpdateDto.getPassword() != null) {
                String hashedPassword = passwordEncoder.encode(customerUpdateDto.getPassword());
                existingCustomer.setPassword(hashedPassword);
            }
            return customerRepository.update(existingCustomer);
        } else {
            throw new CustomerNotFoundException(id);
        }
    }

    @Transactional
    public ArrayList<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

}
