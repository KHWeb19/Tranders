package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommunityBoardListResponse {

    private Long boardNo;

    private String usedSubject;

    private String title;

    private String writer;

    private String region;

    private String content;

    private String profileImg;

    private String imgSrc;

    private String createdDate;

    public CommunityBoardListResponse(Long boardNo, String usedSubject, String title, String writer, String region, String content, String profileImg, String imgSrc, String createdDate) {
        this.boardNo = boardNo;
        this.usedSubject = usedSubject;
        this.title = title;
        this.writer = writer;
        this.region = region;
        this.content = content;
        this.profileImg = profileImg;
        this.imgSrc = imgSrc;
        this.createdDate = createdDate;
    }
}
