package com.example.marketback.service.chatting;


import com.example.marketback.entity.chatting.ChatRoom;

import java.util.List;

public interface ChatRoomService {
    public void register (ChatRoom chatRoom);
    public List<ChatRoom> list ();
}
