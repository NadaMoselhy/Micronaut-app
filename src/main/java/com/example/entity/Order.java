package com.example.entity;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Serdeable
@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // generation auto lets the database decide which generation type is best for this database
    private Long id;

    @NotNull
    @Column(name = "totalPrice" , nullable = false)
    private double totalPrice;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;


}
