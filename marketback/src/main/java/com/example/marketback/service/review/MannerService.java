package com.example.marketback.service.review;

import com.example.marketback.entity.chatting.ChatRoom;
import com.example.marketback.entity.review.Manner;

public interface MannerService {
    public void register (Manner manner, String memberId);
    public Manner read (String memberId);
}
