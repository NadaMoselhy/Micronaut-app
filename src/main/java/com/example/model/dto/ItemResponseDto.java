package com.example.model.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Serdeable
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDto {

    private Long id;


    private String name;


    private long stock;


    private long price;


    private String description;


    private String category;

}
