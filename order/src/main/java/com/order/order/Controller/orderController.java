package com.order.order.Controller;
import com.order.order.kafka.kafkaProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.order.order.model.Order;

@RestController
@RequestMapping("/orders")
public class orderController {

    private  kafkaProducer producer;

    public orderController(kafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public String createOrder(@RequestBody Order order) {
        producer.sendOrder(order);
        return "Order sent successfully!";
    }
}