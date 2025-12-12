package com.order.order.kafka;


import com.order.order.model.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class kafkaProducer {

    private  KafkaTemplate<String, Order> kafkaTemplate;

    public kafkaProducer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(Order order) {
        kafkaTemplate.send("orders-topic", order);
        System.out.println("Sent Order to Kafka: " + order);
    }
}