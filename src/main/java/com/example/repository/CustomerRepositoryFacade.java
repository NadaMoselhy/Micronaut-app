package com.example.repository;
import io.micronaut.data.model.Page;
import com.example.model.entity.Customer;
import com.example.exception.CustomerNotFoundException;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


@Singleton
@RequiredArgsConstructor
public class CustomerRepositoryFacade {

    private static final Logger log =  LoggerFactory.getLogger(CustomerRepositoryFacade.class);

    private final CustomerRepository customerRepository;



    @Transactional
    public Customer findById(Long id){
        return customerRepository.findById(id).orElseThrow(
                () -> {
                    log.error("Customer with ID {} not found", id);
                    return new CustomerNotFoundException(id);
                }
        );
    }

    @Transactional
    public Customer save(Customer customer){
        log.debug("creating new customer with email : {}", customer.getEmail());
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer update(Customer customer){
        log.debug("updating customer info with email : {}", customer.getEmail());
        return  customerRepository.update(customer);

    }

    @Transactional
    public Page<Customer> findAll(Pageable pageable){
        log.debug("Fetching all customers with pagination: {}", pageable);
        return customerRepository.findAll(pageable);
    }


    public boolean existsById(Long id){
        return customerRepository.existsById(id);
    }


    public boolean existsByEmail(String email){
        return customerRepository.existsByEmail(email);
    }

    public void deleteById(Long id){
        log.debug("deleting  customer with id {}", id);
        customerRepository.deleteById(id);
    }
    

}
