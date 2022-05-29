package com.example.marketback.controller.member;

import com.example.marketback.entity.Member;
import com.example.marketback.request.MemberLoginRequest;
import com.example.marketback.request.MemberRegisterRequest;
import com.example.marketback.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public void login(@RequestBody MemberLoginRequest memberLoginRequest){
        log.info("login : " + memberLoginRequest.getId());
        //Spring security가 시도함!
    }

    @PostMapping("/register")
    public void registerMember(@RequestBody Member member){
        log.info("registerMember() : " + member.getId()+", " + member.getPassword());

        memberService.register(member);
    }

    @GetMapping("/home")
    public String home(){

        return "<h2>Test</h2>";
    }

    @PostMapping("/token")
    public String token(){
        return "<h2>token</h2>";
    }
}
