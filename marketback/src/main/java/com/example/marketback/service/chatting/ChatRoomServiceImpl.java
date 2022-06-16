package com.example.marketback.service.chatting;

import com.example.marketback.controller.chatting.ChatRoomRequest;
import com.example.marketback.entity.chatting.ChatRoom;
import com.example.marketback.repository.chatting.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ChatRoomServiceImpl implements ChatRoomService{

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Override
    public void register(ChatRoom chatRoom) {
        chatRoomRepository.save(chatRoom);
    }

    @Override
    public List<ChatRoom> list() {
        return chatRoomRepository.findAll(Sort.by(Sort.Direction.DESC, "roomNo"));
    }

    @Override
    public void modify(ChatRoomRequest chatRoomRequest) {
        ChatRoom chatRoomEntity = new ChatRoom(
                chatRoomRequest.getRoomNo(),
                chatRoomRequest.getAppointTime()
        );
        chatRoomRepository.save(chatRoomEntity);
    }
}
