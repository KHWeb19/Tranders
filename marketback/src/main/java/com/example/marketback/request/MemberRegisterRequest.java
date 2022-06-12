package com.example.marketback.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberRegisterRequest {

    private String id;
    private String pw;
    private String name;
    private String phoneNum;
    private String region;
    private Integer auth;

    public MemberRegisterRequest(String id, String pw, String name, String phoneNum, String region, Integer auth) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phoneNum = phoneNum;
        this.region = region;
        this.auth = auth;
    }
}
