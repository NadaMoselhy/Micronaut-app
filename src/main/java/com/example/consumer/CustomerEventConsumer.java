package com.example.consumer;

import com.example.event.CustomerCreatedEvent;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@KafkaListener(groupId = "customer-logger-group" , offsetReset= OffsetReset.EARLIEST)
public class CustomerEventConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerEventConsumer.class);
    @Topic("customer")
    public void receive(@KafkaKey  String key , CustomerCreatedEvent event){
        LOG.info("📥 Event received: key={}, id={}, email={}", key, event.getCustomerId(), event.getEmail());
    }

}
