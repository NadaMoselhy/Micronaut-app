package com.example.DTO;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
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
