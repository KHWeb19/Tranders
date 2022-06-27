package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BossBackProfileImg {
    private String fileName;

    public BossBackProfileImg(String fileName) {
        this.fileName = fileName;
    }
}
