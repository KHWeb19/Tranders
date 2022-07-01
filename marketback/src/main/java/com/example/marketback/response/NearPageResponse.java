package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NearPageResponse {

    private Long nearNo;

    private String placeName;

    private String category;

    private String phoneNumber;

    private String marketHomePage;

    private String address;

    private String storeRegion;

    private String lat;

    private String lng;

    private Integer reviewCount;

    private Integer communityCount;

    public NearPageResponse(Long nearNo, String placeName, String category, String phoneNumber, String marketHomePage, String address, String storeRegion, String lat, String lng, Integer reviewCount, Integer communityCount) {
        this.nearNo = nearNo;
        this.placeName = placeName;
        this.category = category;
        this.phoneNumber = phoneNumber;
        this.marketHomePage = marketHomePage;
        this.address = address;
        this.storeRegion = storeRegion;
        this.lat = lat;
        this.lng = lng;
        this.reviewCount = reviewCount;
        this.communityCount = communityCount;
    }
}
