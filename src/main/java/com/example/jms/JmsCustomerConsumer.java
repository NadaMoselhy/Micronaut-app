package com.example.jms;

import io.micronaut.jms.annotations.JMSListener;
import io.micronaut.jms.annotations.Queue;
import io.micronaut.messaging.annotation.MessageBody;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import static io.micronaut.jms.activemq.classic.configuration.ActiveMqClassicConfiguration.CONNECTION_FACTORY_BEAN_NAME;

@JMSListener(CONNECTION_FACTORY_BEAN_NAME)
public class JmsCustomerConsumer {
    private final static Logger LOG = LoggerFactory.getLogger(JmsCustomerConsumer.class);

    @Queue(value = "customer-queue")
    public void receive(@MessageBody String message){
        LOG.info("message received from jms with content : {}", message);
    }
}
