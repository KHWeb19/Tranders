package com.example.marketback.controller.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/test")
    public void test(){
        log.info("Test");
        System.out.println("test");
    }
}