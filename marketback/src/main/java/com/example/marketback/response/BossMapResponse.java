package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BossMapResponse {

    private Long bossNo;
    private String placeName;
    private String lat;
    private String lng;

    private String region;

    private String category;

    private Integer reviewCount;

    public BossMapResponse(Long bossNo, String placeName, String lat, String lng, String region, String category, Integer reviewCount) {
        this.bossNo = bossNo;
        this.placeName = placeName;
        this.lat = lat;
        this.lng = lng;
        this.region = region;
        this.category = category;
        this.reviewCount = reviewCount;
    }
}
