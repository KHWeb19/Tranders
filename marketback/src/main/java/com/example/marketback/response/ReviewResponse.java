package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewResponse {

    private Long reviewNo;
    private String name;
    private String region;
    private String content;
    private String profileImg;
    private String state;

    public ReviewResponse(Long reviewNo, String name, String region, String content, String profileImg, String state) {
        this.reviewNo = reviewNo;
        this.name = name;
        this.region = region;
        this.content = content;
        this.profileImg = profileImg;
        this.state = state;
    }
}