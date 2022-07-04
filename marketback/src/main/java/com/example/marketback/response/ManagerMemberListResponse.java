package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ManagerMemberListResponse {

    private Long memberNo;

    private String id;
    private String name;
    private String role;

    public ManagerMemberListResponse(Long memberNo, String id, String name, String role) {
        this.memberNo = memberNo;
        this.id = id;
        this.name = name;
        this.role = role;
    }
}
