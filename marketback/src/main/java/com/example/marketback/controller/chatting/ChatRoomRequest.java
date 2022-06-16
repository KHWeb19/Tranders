package com.example.marketback.controller.chatting;

import lombok.Data;

@Data
public class ChatRoomRequest {
    private Long roomNo;
    private String appointTime;

    public ChatRoomRequest (Long roomNo, String appointTime){
        this.roomNo = roomNo;
        this.appointTime = appointTime;
    }
}

