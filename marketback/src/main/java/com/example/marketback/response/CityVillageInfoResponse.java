package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CityVillageInfoResponse {
    private String city;
    private String district;
    private String villageName;
    private Integer searchId;

    public CityVillageInfoResponse(String city, String district, String villageName, Integer searchId) {
        this.city = city;
        this.district = district;
        this.villageName = villageName;
        this.searchId = searchId;
    }
}
