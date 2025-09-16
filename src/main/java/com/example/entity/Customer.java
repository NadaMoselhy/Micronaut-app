package com.example.entity;

import com.example.dto.CustomerResponseDto;
import com.example.dto.CustomerSignUpDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Serdeable
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name" , nullable = false)
    private String name;

    @NotNull
    @Column(name = "email" , nullable = false)
    private String email;

    @NotNull
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(name = "password" , nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    Set<Order> orders = new HashSet<>();



    public CustomerResponseDto toDTO() {
        return CustomerResponseDto.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .build();
    }

    public Customer fromDto(CustomerSignUpDto customerSignUpDto){
        return Customer.builder()
                .email(customerSignUpDto.getEmail())
                .phoneNumber(customerSignUpDto.getPhoneNumber())
                .name(customerSignUpDto.getName())
                .build();
    }

}
