package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BossCouponMemberResponse {
    private String name;
    private String profileImg;

    public BossCouponMemberResponse(String name, String profileImg) {
        this.name = name;
        this.profileImg = profileImg;
    }
}
