package com.example.marketback.controller.chatting;

import com.example.marketback.entity.chatting.ChatRoom;
import com.example.marketback.entity.member.Member;
import com.example.marketback.service.chatting.ChatRoomService;
import com.example.marketback.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/chatting")
//@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping("/register/{member1No}/{member2No}/{productNo}")
    public void chatRoomRegister(@RequestBody ChatRoom chatRoom, @PathVariable("member1No") Long member1No, @PathVariable("member2No") Long member2No, @PathVariable("productNo") Long productNo) {
        log.info("chatRoomRegister()");

        chatRoomService.register(chatRoom, member1No, member2No, productNo);
    }

    @PostMapping("/register/{member1No}/{member2No}")
    public void bossRegister(@RequestBody ChatRoom chatRoom, @PathVariable("member1No") Long member1No, @PathVariable("member2No") Long member2No) {
        log.info("chatRoomRegister()");

        chatRoomService.bossRegister(chatRoom, member1No, member2No);
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

//    @GetMapping("/{member1}/{member2}")
//    public ChatRoom chatRoomMove(@PathVariable("member1") Long member1, @PathVariable("member2") Long member2) {
//        log.info("chatRoomRead()");
//
//        return chatRoomService.move(member1, member2);
//    }

    @PutMapping("/{roomNo}")
    public ChatRoomRequest chatRoomModify (
            @PathVariable("roomNo") Long roomNo,
            @RequestBody ChatRoomRequest chatRoomRequest) {
        log.info("chatRoomModify(): " + chatRoomRequest);

        chatRoomService.modify(chatRoomRequest, roomNo);

        return chatRoomRequest;
    }

    @PutMapping("/last/{roomNo}")
    public ChatRoomRequest chatRoomLast (
            @PathVariable("roomNo") Long roomNo,
            @RequestBody ChatRoomRequest chatRoomRequest) {
        log.info("chatRoomLast(): " + chatRoomRequest);

        chatRoomService.last(chatRoomRequest, roomNo);

        return chatRoomRequest;
    }

    @ResponseBody
    @PostMapping("/uploadImg")
    public String requestUploadFile (@RequestParam("fileList") List<MultipartFile> fileList) {
        try {
            for (MultipartFile multipartFile: fileList) {
                log.info("requestUploadFile() - Make file:" + multipartFile.getOriginalFilename());

                FileOutputStream writer = new FileOutputStream(
                        "../marketfront/src/assets/chatting/" + multipartFile.getOriginalFilename()
                );
                writer.write(multipartFile.getBytes());
                writer.close();
            }
        } catch (Exception e) {
            return "Upload Fail!";
        }

        log.info("requestUploadFile(): Success!!!");

        return "Upload Success!";
    }

    @PutMapping("/charge")
    public void chargeModify(@RequestBody Member member){
        log.info("chargeModify" + member.getMoney());

        chatRoomService.charge(member);
    }

    @PutMapping("/pay/{productNo}")
    public void payModify(@RequestBody Member member, @PathVariable("productNo") Long productNo){
        log.info("chargeModify" + member.getMoney() + productNo);

        chatRoomService.pay(member, productNo);
    }

}
