package com.example.marketback.service.chatting;

import com.example.marketback.entity.chatting.Message;
import com.example.marketback.repository.chatting.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    MessageRepository messageRepository;

    @Override
    public void register(Message message) {
        messageRepository.save(message);
    }

    @Override
    public List<Message> list() {
        return messageRepository.findAll(Sort.by(Sort.Direction.DESC, "messageNo"));
    }
}
