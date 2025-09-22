package com.example.repository;

import com.example.model.entity.Item;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Item, Long> {
    Page<Item> findByCategory(String Category, Pageable pageable);

    Page<Item> findAll(Pageable pageable);

    Optional<Item> findById(Long id);

    Item save(Item item);

    Item update(Item item);

    void deleteById(Long id);

    boolean existsByName(String name);

    boolean existsByCategory(String category);

    Page<Item> findByPriceBetween(double minPrice, double maxPrice, Pageable pageable);

    Page<Item> findByNameContainingIgnoreCase(String name, Pageable pageable);


}
