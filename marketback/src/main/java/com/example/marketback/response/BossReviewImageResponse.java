package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BossReviewImageResponse {

    private String imgName;
    private Long No;

    public BossReviewImageResponse(String imgName, Long no) {
        this.imgName = imgName;
        No = no;
    }
}
