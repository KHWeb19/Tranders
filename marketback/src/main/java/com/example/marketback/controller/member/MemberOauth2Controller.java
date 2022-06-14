package com.example.marketback.controller.member;

import com.example.marketback.config.auth.PrincipalDetails;
import com.example.marketback.entity.Member;
import com.example.marketback.util.kakao.KakaoProfile;
import com.example.marketback.util.kakao.OAuthToken;
import com.example.marketback.service.member.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;


@RestController
@RequestMapping("/login/oauth2/code")
@Slf4j
public class MemberOauth2Controller {

    @Autowired
    private MemberService memberService;

    @GetMapping("/kakao")
    public ResponseEntity<Object> createAuthenticationTokenByKakao(@RequestParam(value = "code") String code) throws URISyntaxException {
        log.info("kakao login");
        System.out.println(code);

        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("grant_type", "authorization_code");
        params.add("client_id", "1f6ab7138670a91099012c0341d8fe60");
        params.add("redirect_uri", "http://localhost:7777/login/oauth2/code/kakao");
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
        //System.out.println("카카오 id" + kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
        System.out.println("카카오 id" + kakaoProfile.getKakao_account().getEmail());
        System.out.println("카카오 name" + kakaoProfile.getProperties().getNickname());
        UUID tempPassword = UUID.randomUUID();
        System.out.println("패스워드: " + tempPassword);

        // 강제로 회원가입시키는것
        Member memberEntity = Member.builder()
                .id(kakaoProfile.getKakao_account().getEmail())
                .password(String.valueOf(tempPassword))
                .name(kakaoProfile.getProperties().getNickname())
                .phoneNumber("kakaoMember")
                .region("kakaoMember")
                .roles("ROLE_USER")
                .build();

        Member member = memberService.registerMember(memberEntity.getId());

        if(member == null){
            log.info("회원가입을 하겠습니다.");
            memberService.register(memberEntity);
        }

        log.info("로그인을 하겠습니다");
        PrincipalDetails principalDetails = new PrincipalDetails(memberEntity);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberEntity.getId(), null, principalDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

//        return "카카오 로그인 인증 완료, code" + code;

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/");

        URI redirectUri = new URI("http://localhost:8080/");
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setLocation(redirectUri);

        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

}
