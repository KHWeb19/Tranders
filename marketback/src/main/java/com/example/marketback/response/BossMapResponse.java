package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BossMapResponse {

    private String placeName;
    private String lat;
    private String lng;

    public BossMapResponse(String placeName, String lat, String lng) {
        this.placeName = placeName;
        this.lat = lat;
        this.lng = lng;
    }
}
