package com.example.marketback.repository.manager;

import com.example.marketback.entity.manager.Report;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findTop5ByOrderByCreateDateDesc();


    @Query("select r from Report r where r.reportNo = :reportNo")
    Report findByReportNo(Long reportNo);

    @Query("select r from Report r where r.productBoard.productNo = :productNo")
    List<Report> findReportListByProductNo(Long productNo);

    List<Report> findByCreateDateStartingWith(String toString);
}
