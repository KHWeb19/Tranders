package com.example.marketback.service.chatting;

import com.example.marketback.entity.chatting.Message;
import com.example.marketback.repository.chatting.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class KafkaConsumer implements MessageService{

    @Autowired
    MessageRepository messageRepository;

    @KafkaListener(topics = "test", groupId = "foo")
    public void consume(Message message) {
        System.out.println(String.format("Consumed message : %s", message));
        messageRepository.save(message);
    }




    @Override
    public List<Message> list() {
        return messageRepository.findAll();
    }
}