package com.example.marketback.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberCouponRequest {

    private Long couponNo;
    private String Id;

    public MemberCouponRequest(Long couponNo, String id) {
        this.couponNo = couponNo;
        Id = id;
    }
}
