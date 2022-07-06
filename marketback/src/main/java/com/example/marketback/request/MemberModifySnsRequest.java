package com.example.marketback.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberModifySnsRequest {

    private String id;
    private String name;

    public MemberModifySnsRequest(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
