package com.example.marketback.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberLoginRequest {

    private String id;
    private String password;

    public MemberLoginRequest(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
