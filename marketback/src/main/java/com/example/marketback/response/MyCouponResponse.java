package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MyCouponResponse {

    private Long couponNo;

    private String couponName;
    private String couponInfo;
    private String couponDate;
    private String couponState;

    public MyCouponResponse(Long couponNo, String couponName, String couponInfo, String couponDate, String couponState) {
        this.couponNo = couponNo;
        this.couponName = couponName;
        this.couponInfo = couponInfo;
        this.couponDate = couponDate;
        this.couponState = couponState;
    }
}
