package com.example.model.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Serdeable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSignUpDto {

    @NotBlank
    private String name;

    @NotBlank
    @Email(message = "email should be a valid email")
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotBlank
    private String phoneNumber;




}
