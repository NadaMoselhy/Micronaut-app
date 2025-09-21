package com.example.event;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Serdeable
public class CustomerCreatedEvent {
    private Long customerId;
    private String name;
    private String email;
}
