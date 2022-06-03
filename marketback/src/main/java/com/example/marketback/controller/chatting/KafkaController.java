package com.example.marketback.controller.chatting;

import com.example.marketback.entity.chatting.Message;
import com.example.marketback.service.chatting.KafkaProducer;
import com.example.marketback.service.chatting.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private MessageService messageService;

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestBody String message) {
        this.producer.sendMessage(message);

        return "success";
    }


    @PostMapping("/register")
    public void messageRegister(@Validated @RequestBody Message message) {
        log.info("messageRegister()");

        messageService.register(message);
    }

    @GetMapping("/list")
    public List<Message> messageList() {
        log.info("messageList()");

        return messageService.list();
    }
}

