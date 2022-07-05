package com.example.marketback.service.report;

import com.example.marketback.request.ReportRequest;

import java.util.List;

public interface ReportService {
    void saveReport(ReportRequest reportRequest);

    void saveReportImg(List<String> fileName, ReportRequest reportRequest);
}
