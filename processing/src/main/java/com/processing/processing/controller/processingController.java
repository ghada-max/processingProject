package com.processing.processing.controller;

import com.processing.processing.dto.orderProcessedEvent;
import com.processing.processing.producer.processedeventProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/processing")
public class processingController {
    private processedeventProducer producer;

}
