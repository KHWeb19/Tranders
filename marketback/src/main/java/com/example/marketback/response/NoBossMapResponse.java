package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoBossMapResponse {

    private Long nearNo;
    private String placeName;
    private String lat;
    private String lng;

    private String region;

    private String category;

    private Integer reviewCount;

    public NoBossMapResponse(Long nearNo, String placeName, String lat, String lng, String region, String category, Integer reviewCount) {
        this.nearNo = nearNo;
        this.placeName = placeName;
        this.lat = lat;
        this.lng = lng;
        this.region = region;
        this.category = category;
        this.reviewCount = reviewCount;
    }
}
