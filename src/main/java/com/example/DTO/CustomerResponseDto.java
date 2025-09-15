package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Serdeable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerResponseDto {

    private long id;

    private String name;

    private String email;

    private String phoneNumber;


}
