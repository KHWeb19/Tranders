package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BossPriceMenuResponse {

    private Long menuNo;
    private String menuName;
    private String menuPrice;
    private String menuInfo;

    public BossPriceMenuResponse(Long menuNo, String menuName, String menuPrice, String menuInfo) {
        this.menuNo = menuNo;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuInfo = menuInfo;
    }
}
