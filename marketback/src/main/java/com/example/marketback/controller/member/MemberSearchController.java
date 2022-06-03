package com.example.marketback.controller.member;

import com.example.marketback.request.MemberLoginRequest;
import com.example.marketback.service.member.MemberService;
import com.example.marketback.service.member.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/search")
public class MemberSearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("/id")
    public String SearchId(@RequestBody HashMap<String, String> searchId){
        log.info("searchId(): "+searchId.get("name")+", "+searchId.get("phoneNumber"));

        return searchService.searchId(searchId.get("name"), searchId.get("phoneNumber"));
    }

    @PostMapping("/pw")
    public String SearchPw(@RequestBody HashMap<String, String> searchPw){
        log.info("searchPw(): "+searchPw.get("name"));

        return searchService.searchPw(searchPw.get("id"), searchPw.get("name"), searchPw.get("phoneNumber"));
    }

    @PostMapping("/changePw")
    public void ChangePw(@RequestBody MemberLoginRequest memberLoginRequest){
        log.info("changePw()"+ memberLoginRequest.getId());

        searchService.changePw(memberLoginRequest);
    }
}
