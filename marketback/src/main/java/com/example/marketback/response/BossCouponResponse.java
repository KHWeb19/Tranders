package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BossCouponResponse {

    private Long couponNo;
    private String couponName;
    private String couponInfo;
    private String couponDate;
    private Integer couponMax;
    private Integer giveCoupon;
    private String couponState;

    public BossCouponResponse(Long couponNo, String couponName, String couponInfo, String couponDate, Integer couponMax, Integer giveCoupon) {
        this.couponNo = couponNo;
        this.couponName = couponName;
        this.couponInfo = couponInfo;
        this.couponDate = couponDate;
        this.couponMax = couponMax;
        this.giveCoupon = giveCoupon;
    }
}
