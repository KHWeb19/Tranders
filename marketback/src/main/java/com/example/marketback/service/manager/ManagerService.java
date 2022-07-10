package com.example.marketback.service.manager;

import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.response.ManagerMemberListResponse;
import com.example.marketback.response.ManagerReportReadResponse;
import com.example.marketback.response.ManagerReportRecentResponse;

import java.util.List;

public interface ManagerService {
    List<ManagerReportRecentResponse> findRecentReport();

    List<ManagerReportRecentResponse> findReportList(Integer pageable);

    Integer getTotalPage();

    ManagerReportReadResponse readReport(Long reportNo);

    List<String> readImage(Long reportNo);

    ProductBoard readBoard(Long reportNo);

    void deleteBoard(Long reportNo);

    void reportState(Long reportNo);

    List<ManagerMemberListResponse> memberList(Integer page);

    Integer getMemberTotalPage();

    void changeRole(Long memberNo);

    Integer todayRegisterMember();

    Integer todayReport();

    void removeMember(String id, Integer num);
}
