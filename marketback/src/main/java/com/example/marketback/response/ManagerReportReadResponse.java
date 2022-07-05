package com.example.marketback.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ManagerReportReadResponse {

    private Long reportNo;
    private String category1;
    private String category2;
    private String content;
    private String toMember;
    private String fromMember;

    private String state;

    public ManagerReportReadResponse(Long reportNo, String category1, String category2, String content, String toMember, String fromMember, String state) {
        this.reportNo = reportNo;
        this.category1 = category1;
        this.category2 = category2;
        this.content = content;
        this.toMember = toMember;
        this.fromMember = fromMember;
        this.state = state;
    }
}
