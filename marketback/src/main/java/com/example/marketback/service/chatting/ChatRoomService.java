package com.example.marketback.service.chatting;


import com.example.marketback.controller.chatting.ChatRoomRequest;
import com.example.marketback.entity.chatting.ChatRoom;
import com.example.marketback.entity.member.Member;

import java.util.List;

public interface ChatRoomService {
    public void register (ChatRoom chatRoom, Long member1No);
    public List<ChatRoom> list (Long memberNo);
    public ChatRoom read (Long roomNo);
    public ChatRoom move (Long member1, Long member2);
    public void modify (ChatRoomRequest chatRoomRequest, Long roomNo);
}
