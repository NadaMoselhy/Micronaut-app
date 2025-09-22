package com.example.model.entity;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Serdeable
@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name" , nullable = false)
    private String name;

    @NotNull
    @Column(name = "stock" , nullable = false)
    private long stock;

    @NotNull
    @Column(name = "price" , nullable = false)
    private long price;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "price" , nullable = false)
    private String category;


}
