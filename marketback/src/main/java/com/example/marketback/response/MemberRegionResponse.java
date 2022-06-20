package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class MemberRegionResponse {
    private String lat;
    private String lng;
    private String region;

    public MemberRegionResponse(String lat, String lng, String region) {
        this.lat = lat;
        this.lng = lng;
        this.region = region;
    }
}
