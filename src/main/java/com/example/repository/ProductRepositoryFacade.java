package com.example.repository;

import com.example.exception.ProductNotFoundException;
import com.example.model.entity.Item;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@RequiredArgsConstructor
public class ProductRepositoryFacade {

    private final ProductRepository productRepository;
    private static final Logger LOG = LoggerFactory.getLogger(ProductRepositoryFacade.class);

    public Item findById(Long id){
        return productRepository.findById(id).orElseThrow(
                () -> {
                    LOG.error("the product with id {} is not found", id);
                    return new ProductNotFoundException(id);
                }
        );
    }

    public Page<Item> findByCategory(String category, Pageable pageable){
        return productRepository.findByCategory(category,pageable);
    }

    public Item save(Item item){
        LOG.info("saving item with name : {}", item.getName() );
        return productRepository.save(item);
    }

    public Item update(Item item){
        LOG.info("updating item with name : {}", item.getName() );
        return productRepository.update(item);
    }

    public void deleteById(Long id){
        LOG.info("deleting item with id: {}", id );
        productRepository.deleteById(id);
    }

   public boolean existsByName(String name){
        return  productRepository.existsByName(name);
   }

    public Page<Item> findByPriceBetween(double minPrice, double maxPrice, Pageable pageable){
            return  productRepository.findByPriceBetween(minPrice,maxPrice,pageable);
    }

    public  Page<Item> findByNameContainingIgnoreCase(String name, Pageable pageable){
        return productRepository.findByNameContainingIgnoreCase(name,pageable);
    }

    public Page<Item> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public boolean existsByCategory(String category){
        return productRepository.existsByCategory(category);
    }
}
