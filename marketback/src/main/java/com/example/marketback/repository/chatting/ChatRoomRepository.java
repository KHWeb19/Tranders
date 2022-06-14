package com.example.marketback.repository.chatting;

import com.example.marketback.entity.chatting.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
