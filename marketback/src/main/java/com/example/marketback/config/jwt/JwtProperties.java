package com.example.marketback.config.jwt;

public interface JwtProperties {
    String SECRET = "52market";
    int EXPIRATION_TIME_ACCESS = 60 * 60 * 1000 * 3;
    int EXPIRATION_TIME_REFRESH = 60 * 60 * 1000 * 5;
    String TOKEN_PREFIX = "Bearer ";
}
