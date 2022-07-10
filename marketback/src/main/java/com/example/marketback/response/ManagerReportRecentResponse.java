package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ManagerReportRecentResponse {

    private Long reportNo;
    private String category1;
    private String category2;
    private String writer;
    private String state;

    public ManagerReportRecentResponse(Long reportNo, String category1, String category2, String writer, String state) {
        this.reportNo = reportNo;
        this.category1 = category1;
        this.category2 = category2;
        this.writer = writer;
        this.state = state;
    }
}
