package com.example.model.entity;


import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Serdeable
@Entity
@Table(name = "order_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;

    @ManyToOne
    @MapsId("order_id")
    private Order order;

    @ManyToOne
    @MapsId("item_id")
    private Item item;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private double price;



}
