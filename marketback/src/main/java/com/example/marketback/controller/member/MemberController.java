package com.example.marketback.controller.member;

import com.example.marketback.util.KakaoProfile;
import com.example.marketback.entity.Member;
import com.example.marketback.util.OAuthToken;
import com.example.marketback.service.member.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback(String code) {

        // POST 방식으로 key, value 데이터를 요청(카카오톡으로)
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 객체 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("grant_type", "authorization_code");
        params.add("client_id", "1f6ab7138670a91099012c0341d8fe60");
        params.add("redirect_uri", "http://localhost:7777/member/auth/kakao/callback");
        params.add("code", code);

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        // Http 요청하기 => Post 방식으로 => response 변수의 응답 받음
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();

        OAuthToken oAuthToken = null;
        try {
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        assert oAuthToken != null;
        System.out.println("카카오 엑세스 토큰: " + oAuthToken.getAccess_token());

        RestTemplate rt2 = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
        //headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        //headers2.add("property_keys", "kakao_account.email");

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);

        // Http 요청하기 => Post 방식으로 => response 변수의 응답 받음
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        ObjectMapper objectMapper2 = new ObjectMapper();

        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        assert kakaoProfile != null;
        System.out.println("카카오 아이디: " + kakaoProfile.getId());
        System.out.println("카카오 이메일: " + kakaoProfile.getKakao_account().getEmail());

        // id, pw, name, phoneNum(kakao),

        return response2.getBody();
    }
}