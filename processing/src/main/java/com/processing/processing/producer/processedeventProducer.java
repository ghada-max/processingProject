package com.processing.processing.producer;

import com.processing.processing.dto.orderProcessedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class processedeventProducer {


    private KafkaTemplate<String, orderProcessedEvent> kafkaTemplate;

    public processedeventProducer(KafkaTemplate<String, orderProcessedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotif(orderProcessedEvent order) {
        kafkaTemplate.send("processedOrders-topic", order);
        System.out.println("Sent Order to Kafka: " + order);
    }
}
