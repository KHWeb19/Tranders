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
//@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping("/register/{memberNo}")
    public void chatRoomRegister(@PathVariable("memberNo") Long memberNo, @Validated @RequestBody ChatRoom chatRoom) {
        log.info("chatRoomRegister()");

        chatRoomService.register(chatRoom, memberNo);
    }

    @GetMapping("/list/{memberNo}")
    public List<ChatRoom> chatRoomList(@PathVariable("memberNo") Long memberNo) {
        log.info("chatRoomList()");

        return chatRoomService.list(memberNo);
    }

    @GetMapping("/{roomNo}")
    public ChatRoom chatRoomRead(@PathVariable("roomNo") Long roomNo) {
        log.info("chatRoomRead()");

        return chatRoomService.read(roomNo);
    }

    @PutMapping("/{roomNo}")
    public ChatRoomRequest chatRoomModify (
            @PathVariable("roomNo") Long roomNo,
            @RequestBody ChatRoomRequest chatRoomRequest) {
        log.info("chatRoomModify(): " + chatRoomRequest);

        chatRoomService.modify(chatRoomRequest, roomNo);

        return chatRoomRequest;
    }


}
