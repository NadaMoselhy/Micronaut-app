package com.example.repository;

import com.example.entity.Customer;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @NonNull
    Optional<Customer> findById(Long id);

    Customer findByEmail(String email);

    Customer save(Customer customer);

    boolean existsById(Long id);

    boolean existsByEmail(String email);

    void deleteById(Long id);


}
