package com.example.marketback.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.marketback.config.auth.PrincipalDetails;
import com.example.marketback.config.jwt.JwtProperties;
import com.example.marketback.entity.member.Member;
import com.example.marketback.repository.member.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private final MemberRepository memberRepository;

    public CustomAuthorizationFilter(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("인증이나 권한이 필요한 주소 요청이 됨 ");
        System.out.println(request.getRequestURI());

        if(request.getServletPath().equals("/member/login") || request.getServletPath().equals("/member/refreshToken") || request.getServletPath().equals("/oauth2/code/**")){
            filterChain.doFilter(request, response);
        }else {
            System.out.println("doFilterInternal");

            String authorizationHeader = request.getHeader(AUTHORIZATION);

            if(authorizationHeader != null && authorizationHeader.startsWith(JwtProperties.TOKEN_PREFIX)){
                try {
                    String token = authorizationHeader.replace(JwtProperties.TOKEN_PREFIX, "");
                    log.info("token" + token);

                   Algorithm algorithm = Algorithm.HMAC256(JwtProperties.SECRET.getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String id = decodedJWT.getSubject();
                    log.info("id: "+id);

                    Member memberEntity = memberRepository.findByMemberId(id);
                    PrincipalDetails principalDetails = new PrincipalDetails(memberEntity);


                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, null, principalDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    filterChain.doFilter(request, response);
                }catch (Exception e){
                    log.error("Error logging in : {}", e.getMessage());
                    response.setHeader("error", e.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    //response.sendError(FORBIDDEN.value());

                    Map<String, String> error = new HashMap<>();
                    error.put("error_message",e.getMessage());

                    response.setContentType(APPLICATION_JSON_VALUE);

                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }

            }else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
