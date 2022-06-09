package com.example.marketback.controller.chatting;

import com.example.marketback.entity.chatting.ChatRoom;
import com.example.marketback.service.chatting.ChatRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/chatting")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping("/register")
    public void chatRoomRegister(@Validated @RequestBody ChatRoom chatRoom) {
        log.info("chatRoomRegister()" + chatRoom);

        chatRoomService.register(chatRoom);
    }

    @GetMapping("/list")
    public List<ChatRoom> chatRoomList() {
        log.info("chatRoomList()");

        return chatRoomService.list();
    }
}
