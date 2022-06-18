package com.example.marketback.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.marketback.config.auth.PrincipalDetails;
import com.example.marketback.config.jwt.JwtProperties;
import com.example.marketback.entity.member.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.jsoup.internal.StringUtil;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            ObjectMapper om = new ObjectMapper();

            Member member = om.readValue(request.getInputStream(), Member.class);

            if (member.getId() == null || member.getPassword() == null) {
                throw new IllegalArgumentException("username or Password is Empty");
            }

            String id = member.getId();
            String password = member.getPassword();

            //System.out.println(member);

            log.info("id: {}, passwd: {}", id, password);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, password);

            return authenticationManager.authenticate(authenticationToken);
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override // 로그인 성공시 호출됨.
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException{
        System.out.println("successfulAuthentication 실행됨!");
        PrincipalDetails member = (PrincipalDetails) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(JwtProperties.SECRET.getBytes());
        String access_token = JWT.create()
                .withSubject(member.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME_ACCESS ))
                .withIssuer(request.getRequestURI())
                .withClaim("name", member.getName())
                .withClaim("memberNo", member.getMemberNo())
                .withClaim("roles", member.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(member.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME_REFRESH))
                .withIssuer(request.getRequestURI())
                .sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token",access_token);
        tokens.put("refresh_token",refresh_token);

        response.setContentType(APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
