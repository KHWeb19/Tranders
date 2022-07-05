package com.example.marketback.controller.review;

import com.example.marketback.entity.chatting.ChatRoom;
import com.example.marketback.entity.review.Manner;
import com.example.marketback.service.review.MannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/manner")
public class MannerController {

    @Autowired
    private MannerService mannerService;

    @PostMapping("/register/{memberId}")
    public void mannerRegister(@RequestBody Manner manner, @PathVariable("memberId") String memberId) {
        log.info("mannerRegister()"+memberId);

        mannerService.register(manner, memberId);
    }

    @GetMapping("/{memberId}")
    public Manner mannerRead(@PathVariable("memberId") String memberId) {
        log.info("mannerRead()");

        return mannerService.read(memberId);
    }
}
