package com.example.service;

import com.example.jms.JmsCustomerProducer;
import com.example.model.dto.CustomerResponseDto;
import com.example.model.dto.CustomerSignUpDto;
import com.example.model.dto.CustomerUpdateDto;
import com.example.model.entity.Customer;
import com.example.event.CustomerCreatedEvent;
import com.example.exception.EmailAlreadyExistsException;
import com.example.model.mapper.CustomerMapper;
import com.example.producer.CustomerEventProducer;
import com.example.repository.CustomerRepositoryFacade;
import com.example.utils.BCryptPasswordEncoder;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

@Singleton
// the service should be a singleton so it gets injected in the controller as a bean and also so that micronaut
// only creates a single instance for the whole app
public class CustomerService {

    private final CustomerRepositoryFacade customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomerEventProducer customerEventProducer;
    private final JmsCustomerProducer jmsCustomerProducer;

    private final CustomerMapper mapper;

    public CustomerService(CustomerRepositoryFacade customerRepository, BCryptPasswordEncoder passwordEncoder, CustomerEventProducer customerEventProducer, JmsCustomerProducer jmsCustomerProducer, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerEventProducer = customerEventProducer;
        this.jmsCustomerProducer = jmsCustomerProducer;
        this.mapper = mapper;
    }

    public CustomerResponseDto findCustomerById(Long id) {
        Customer result = customerRepository.findById(id);

            return mapper.toDto(result);

    }

    @Transactional
    public Customer createCustomer(CustomerSignUpDto customerDto) {

        boolean exists = customerRepository.existsByEmail(customerDto.getEmail());
        if (exists) {
            throw new EmailAlreadyExistsException(customerDto.getEmail());
        } else {
            String hashedPassword = passwordEncoder.encode(customerDto.getPassword());
            Customer newCustomer = mapper.fromSignupDto(customerDto);
            newCustomer.setPassword(hashedPassword);


            Customer createdCustomer = customerRepository.save(newCustomer);

            customerEventProducer.sendCustomerInfo(
                    "customer-"+createdCustomer.getId(),
                    CustomerCreatedEvent.builder().customerId(createdCustomer.getId())
                            .email(createdCustomer.getEmail())
                            .name(createdCustomer.getName()).build()
            );

        jmsCustomerProducer.sendCreatedCustomerMessage("created customer with email : " + customerDto.getEmail());


            return createdCustomer;
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
        Customer existingCustomer = customerRepository.findById(id);

            // update the email
            String newEmail = customerUpdateDto.getEmail();

            if (newEmail != null && !newEmail.equals(existingCustomer.getEmail())) {
                if (customerRepository.existsByEmail(newEmail)) {
                    throw new EmailAlreadyExistsException(newEmail);
                }
                existingCustomer.setEmail(newEmail);
            }
            mapper.updateCustomerFromDto(customerUpdateDto,existingCustomer);

            if (customerUpdateDto.getPassword() != null) {
                String hashedPassword = passwordEncoder.encode(customerUpdateDto.getPassword());
                existingCustomer.setPassword(hashedPassword);
            }
            return customerRepository.update(existingCustomer);

    }

    @Transactional
    public Page<Customer> getAllCustomers(int page, int size) {
        return customerRepository.findAll(Pageable.from(page,size));
    }

}
