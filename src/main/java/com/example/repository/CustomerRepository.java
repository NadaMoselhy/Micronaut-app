package com.example.repository;

import com.example.entity.Customer;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @NonNull
    Optional<Customer> findById(Long id);

    ArrayList<Customer> findAll();

    Customer findByEmail(String email);

    Customer save(Customer customer);

    Customer update(Customer customer);

    boolean existsById(Long id);

    boolean existsByEmail(String email);

    void deleteById(Long id);




}
