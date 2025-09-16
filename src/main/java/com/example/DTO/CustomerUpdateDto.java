package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
public class CustomerUpdateDto {

    private String name;

    @Email(message = "Email must be valid")
    private String email;

    private String password;

    @Size(min = 8, message = "Password must be at least 8 characters")
    private String phoneNumber;

}
