package com.example.marketback.controller.chatting;

import com.example.marketback.entity.chatting.ChatRoom;
import com.example.marketback.entity.member.Member;
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

    @PostMapping("/register/{member1No}/{productNo}")
    public void chatRoomRegister(@RequestBody ChatRoom chatRoom, @PathVariable("member1No") Long member1No, @PathVariable("productNo") Long productNo) {
        log.info("chatRoomRegister()");

        chatRoomService.register(chatRoom, member1No, productNo);
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

    @GetMapping("/{member1}/{member2}")
    public ChatRoom chatRoomMove(@PathVariable("member1") Long member1, @PathVariable("member2") Long member2) {
        log.info("chatRoomRead()");

        return chatRoomService.move(member1, member2);
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
