package com.example.repository;

import com.example.model.entity.Customer;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @NonNull
    Optional<Customer> findById(Long id);

    Page<Customer> findAll(Pageable pageable );

    Customer findByEmail(String email);

    Customer save(Customer customer);

    Customer update(Customer customer);

    boolean existsById(Long id);

    boolean existsByEmail(String email);

    void deleteById(Long id);




}
