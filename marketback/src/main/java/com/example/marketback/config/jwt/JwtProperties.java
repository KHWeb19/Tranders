package com.example.marketback.config.jwt;

public interface JwtProperties {
    String SECRET = "52market";
    int EXPIRATION_TIME_ACCESS = 10 * 60 * 1000;
    int EXPIRATION_TIME_REFRESH = 60 * 60 * 1000 * 3;
    String TOKEN_PREFIX = "Bearer ";
}
