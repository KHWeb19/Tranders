package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NearReviewResponse {

    private String name;
    private String region;
    private String content;
    private String imageName;
    private String state;

    public NearReviewResponse(String name, String region, String content, String imageName, String state) {
        this.name = name;
        this.region = region;
        this.content = content;
        this.imageName = imageName;
        this.state = state;
    }
}
