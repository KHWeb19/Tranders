package com.example.marketback.repository.manager;

import com.example.marketback.entity.manager.Report;
import com.example.marketback.entity.manager.ReportImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportImageRepository extends JpaRepository<ReportImage, Long> {
    @Query("select rei from ReportImage rei where rei.report.reportNo = :reportNo")
    List<ReportImage> findByReportNo(Long reportNo);
}
