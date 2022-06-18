package com.example.marketback.controller.chatting;

import lombok.Data;

@Data
public class ChatRoomRequest {
    private Long roomNo;
    private String appointDate;
    private String appointTime;

    public ChatRoomRequest (Long roomNo, String appointDate, String appointTime){
        this.roomNo = roomNo;
        this.appointDate = appointDate;
        this.appointTime = appointTime;
    }
}

