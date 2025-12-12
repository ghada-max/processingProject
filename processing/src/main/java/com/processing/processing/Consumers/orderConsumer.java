package com.processing.processing.Consumers;
import com.processing.processing.dto.orderProcessedEvent;

import com.processing.processing.dto.Order;
import com.processing.processing.producer.processedeventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class orderConsumer {
    //listener
    //wrapper to dto
    @Autowired
    private  processedeventProducer producer;
    @KafkaListener(topics = "orders-topic", groupId = "processing-group")
    public void consume(Order ord) {
        System.out.println("Order received for processing: " + ord);



        // simulate treatment
        orderProcessedEvent event =  orderProcessedEvent.builder()
                .orderId(ord.getOrderId())
                .status("PROCESSED")
                .build();



        // send to next topic
        producer.sendNotif(event);
    }
}

