package com.example.marketback.service.chatting;


import com.example.marketback.controller.chatting.ChatRoomRequest;
import com.example.marketback.entity.chatting.ChatRoom;

import java.util.List;

public interface ChatRoomService {
    public void register (ChatRoom chatRoom, Long memberNo);
    public List<ChatRoom> list (Long memberNo);
    public ChatRoom read (Long roomNo);
    public void modify (ChatRoomRequest chatRoomRequest, Long roomNo);
}
