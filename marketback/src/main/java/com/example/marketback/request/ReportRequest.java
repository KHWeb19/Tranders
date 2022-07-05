package com.example.marketback.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReportRequest {
    private String category1;
    private String category2;
    private String content;
    private Boolean banCheck;
    private Long productNo;
    private String toId;
    private String fromId;

    public ReportRequest(String category1, String category2, String content, Boolean banCheck, Long productNo, String toId, String fromId) {
        this.category1 = category1;
        this.category2 = category2;
        this.content = content;
        this.banCheck = banCheck;
        this.productNo = productNo;
        this.toId = toId;
        this.fromId = fromId;
    }
}
