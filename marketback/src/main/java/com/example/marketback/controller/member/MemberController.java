package com.example.marketback.controller.member;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.marketback.config.auth.PrincipalDetails;
import com.example.marketback.config.jwt.JwtProperties;
import com.example.marketback.entity.Member;
import com.example.marketback.service.member.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/info")
    public String test3(){
        log.info("test2");
        return "<h2>test2</h2>";
    }
}
