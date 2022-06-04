package com.example.marketback.service.chatting;

import com.example.marketback.entity.chatting.Message;

import java.util.List;

public interface MessageService {
    public void consume (Message message);
    public List<Message> list ();
}
