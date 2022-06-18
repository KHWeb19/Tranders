package com.example.marketback.oauth2;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.marketback.config.auth.PrincipalDetails;
import com.example.marketback.config.jwt.JwtProperties;
import com.example.marketback.entity.member.Member;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.util.CookieUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String targetUrl = "http://localhost:8080/";

        System.out.println(authentication.getPrincipal().toString());

        String name = authentication.getName();
        System.out.println(name);

        PrincipalDetails member = (PrincipalDetails) authentication.getPrincipal();

        Algorithm algorithm = Algorithm.HMAC256(JwtProperties.SECRET.getBytes());
        String access_token = JWT.create()
                .withSubject(member.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME_ACCESS ))
                .withIssuer(request.getRequestURI())
                .withClaim("name", member.getName())
                .withClaim("roles", member.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(member.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME_REFRESH))
                .withIssuer(request.getRequestURI())
                .sign(algorithm);

        System.out.println(access_token);

        Cookie accessToken = new Cookie("access_token", access_token);
        accessToken.setMaxAge(60 * 10);
        accessToken.setPath("/");

        Cookie refreshToken = new Cookie("refresh_token", refresh_token);
        refreshToken.setMaxAge(60 * 10);
        refreshToken.setPath("/");

        response.addCookie(accessToken);
        response.addCookie(refreshToken);

        return targetUrl;
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        //httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

}
