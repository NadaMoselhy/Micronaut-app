package com.example.producer;

import com.example.event.CustomerCreatedEvent;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface CustomerEventProducer {

    @Topic("customer")
    void sendCustomerInfo(@KafkaKey String id, CustomerCreatedEvent createdEvent);
}
