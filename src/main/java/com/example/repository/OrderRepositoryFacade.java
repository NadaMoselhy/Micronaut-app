package com.example.repository;

import com.example.exception.OrderNotFoundException;
import com.example.model.entity.Order;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@RequiredArgsConstructor
public class OrderRepositoryFacade {

    private final OrderRepository orderRepository;
    private static final Logger LOG = LoggerFactory.getLogger(OrderRepositoryFacade.class);


    public Order findById(Long id){
        return orderRepository.findById(id).orElseThrow(
                ()->{
                    LOG.error("Order does not exist with this id: {}", id);
                    return new OrderNotFoundException(id);
                }
        );

    }


    public Page<Order> findByCustomerId(Long id, Pageable pageable){
        return orderRepository.findByCustomerId(id,pageable);
    }

    @Transactional
    public Order save(Order order){
        return orderRepository.save(order);
    }

    @Transactional
    public  Order update(Order order){
        LOG.info("updating order with id {}",order.getId());
        return orderRepository.update(order);
    }

    @Transactional
    public void deleteById(Long id){
        LOG.info("deleting order with id {}",id);
        orderRepository.deleteById(id);
    }

}
