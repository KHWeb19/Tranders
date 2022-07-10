package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BossBackProfileImg {
    private String fileName;
    private Long no;

    public BossBackProfileImg(String fileName, Long no) {
        this.fileName = fileName;
        this.no = no;
    }
}
