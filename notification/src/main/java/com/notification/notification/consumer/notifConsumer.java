package com.notification.notification.consumer;

import com.notification.notification.dto.OrderProcessedEvent;
import com.notification.notification.dto.finalOrder;
import com.notification.notification.mailService.mailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class notifConsumer {


    @Autowired
    public mailService mailServ;
 @KafkaListener(topics="processedOrders-topic",groupId="notif-group")
 public void consumer(OrderProcessedEvent ord){
     finalOrder notiford=new finalOrder();
     notiford.setOrderId(ord.getOrderId());
     notiford.setStatus(ord.getStatus());



     System.out.println("Received processed order: orderId=" + notiford.getOrderId()
             + ", status=" + notiford.getStatus());

     String to = "destinataire@gmail.com";
     String subject = "Nouvelle commande traitée: " + notiford.getOrderId();
     String body = "La commande " + notiford.getOrderId() + " a été traitée avec le statut: " + notiford.getStatus();

     mailServ.sendSimpleMail(to, subject, body);
 }
}
