package com.example.marketback.repository.chatting;

import com.example.marketback.entity.chatting.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
