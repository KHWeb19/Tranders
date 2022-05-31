package com.example.marketback.controller.member;

import com.example.marketback.entity.Member;
import com.example.marketback.request.MemberLoginRequest;
import com.example.marketback.request.MemberRegisterRequest;
import com.example.marketback.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/register")
    public void registerMember(@RequestBody Member member){
        log.info("registerMember() : " + member.getId()+", " + member.getPassword());

        memberService.register(member);
    }

    @GetMapping("/register/{id}")
    public ResponseEntity<Boolean> checkId(@PathVariable String id){
        log.info("checkId(): "+id);

        return ResponseEntity.ok(memberService.checkIdDuplicate(id));
    }
}
