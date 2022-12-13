package com.exam2022.orderservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    public static final String TOPIC = "customer-order-topic";

    @Autowired
    private KafkaTemplate<String, String> template;

    public void sendMessage(String message) {
        template.send(TOPIC, message);
        template.flush();
    }
}
