package com.example.repository;

import com.example.model.entity.Order;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {
    Optional<Order> findById(Long id);

    Page<Order> findByCustomerId(Long id, Pageable pageable);

    Order save(Order order);

    Order update(Order order);

    void deleteById(Long id);



}
