package com.example.marketback.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BossCouponRequest {

    private Long bossNo;
    private String couponName;
    private String couponInfo;
    private String couponDate;
    private Integer couponMax;

    public BossCouponRequest(Long bossNo, String couponName, String couponInfo, String couponDate, Integer couponMax) {
        this.bossNo = bossNo;
        this.couponName = couponName;
        this.couponInfo = couponInfo;
        this.couponDate = couponDate;
        this.couponMax = couponMax;
    }
}
