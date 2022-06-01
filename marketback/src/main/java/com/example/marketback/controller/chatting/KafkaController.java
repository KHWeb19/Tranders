package com.example.marketback.controller.chatting;

import com.example.marketback.service.chatting.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/kafka")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class KafkaController {
    private final KafkaProducer producer;

    @Autowired
    KafkaController(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestBody String message) {
        this.producer.sendMessage(message);

        return "success";
    }
}
