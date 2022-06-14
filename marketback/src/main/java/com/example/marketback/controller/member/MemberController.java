package com.example.marketback.controller.member;

//import com.example.marketback.util.KakaoProfile;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.marketback.config.auth.PrincipalDetails;
import com.example.marketback.config.jwt.JwtProperties;
import com.example.marketback.entity.Member;
//import com.example.marketback.util.OAuthToken;
import com.example.marketback.service.member.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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

//    @GetMapping("/auth/kakao/callback")
//    public @ResponseBody String kakaoCallback(String code) {
//
//        // POST 방식으로 key, value 데이터를 요청(카카오톡으로)
//        RestTemplate rt = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        // HttpBody 객체 생성
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//
//        params.add("grant_type", "authorization_code");
//        params.add("client_id", "1f6ab7138670a91099012c0341d8fe60");
//        params.add("redirect_uri", "http://localhost:7777/member/auth/kakao/callback");
//        params.add("code", code);
//
//        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
//        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
//
//        // Http 요청하기 => Post 방식으로 => response 변수의 응답 받음
//        ResponseEntity<String> response = rt.exchange(
//                "https://kauth.kakao.com/oauth/token",
//                HttpMethod.POST,
//                kakaoTokenRequest,
//                String.class
//        );
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        OAuthToken oAuthToken = null;
//        try {
//            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        assert oAuthToken != null;
//        System.out.println("카카오 엑세스 토큰: " + oAuthToken.getAccess_token());
//
//        RestTemplate rt2 = new RestTemplate();
//        HttpHeaders headers2 = new HttpHeaders();
//        headers2.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
//        //headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//        //headers2.add("property_keys", "kakao_account.email");
//
//        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
//        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);
//
//        // Http 요청하기 => Post 방식으로 => response 변수의 응답 받음
//        ResponseEntity<String> response2 = rt2.exchange(
//                "https://kapi.kakao.com/v2/user/me",
//                HttpMethod.POST,
//                kakaoProfileRequest,
//                String.class
//        );
//
//        ObjectMapper objectMapper2 = new ObjectMapper();
//
//        KakaoProfile kakaoProfile = null;
//        try {
//            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        assert kakaoProfile != null;
//        System.out.println("카카오 아이디: " + kakaoProfile.getId());
//        System.out.println("카카오 이메일: " + kakaoProfile.getKakao_account().getEmail());
//
//        // id, pw, name, phoneNum(kakao),
//
//        return response2.getBody();
//    }

    @PostMapping("/kakao/login")
    public RedirectView kakaoLogin(){

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:7777/login/oauth2/code/kakao");
        return redirectView;
    }


    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        System.out.println(authorizationHeader);
        if(authorizationHeader != null && authorizationHeader.startsWith(JwtProperties.TOKEN_PREFIX)){
            try {
                String refresh_token = authorizationHeader.substring(JwtProperties.TOKEN_PREFIX.length());

                Algorithm algorithm = Algorithm.HMAC256(JwtProperties.SECRET.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);

                String id = decodedJWT.getSubject();
                Member member = memberService.getMember(id);

                PrincipalDetails principalDetails = new PrincipalDetails(member);

                String access_token = JWT.create()
                        .withSubject(member.getId())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME_ACCESS))
                        .withIssuer(request.getRequestURI())
                        .withClaim("roles", principalDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);


                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            }catch (Exception e){
                log.error("Error logging in : {}", e.getMessage());
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("error_message",e.getMessage());

                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }

        }else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @GetMapping("/test1/test")
    public String test(){
        log.info("test");
        return "<h2>test</h2>";
    }

    @GetMapping("/test2")
    public String test2(){
        log.info("test2");
        return "<h2>test2</h2>";
    }
}