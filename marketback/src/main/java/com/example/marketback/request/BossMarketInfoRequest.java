package com.example.marketback.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BossMarketInfoRequest {

    private String id;
    private String phoneNumber;
    private String marketInfo;
    private String marketHomePage;
    private String startTime;
    private String endTime;
    private String address;
    private String lat;
    private String lng;

    public BossMarketInfoRequest(String id, String phoneNumber, String marketInfo, String marketHomePage, String startTime, String endTime, String address, String lat, String lng) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.marketInfo = marketInfo;
        this.marketHomePage = marketHomePage;
        this.startTime = startTime;
        this.endTime = endTime;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }
}
