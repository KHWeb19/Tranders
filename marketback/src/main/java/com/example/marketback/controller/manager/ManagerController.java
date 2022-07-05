package com.example.marketback.controller.manager;

import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.response.ManagerMemberListResponse;
import com.example.marketback.response.ManagerReportReadResponse;
import com.example.marketback.response.ManagerReportRecentResponse;
import com.example.marketback.service.manager.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PostMapping("/reportListRecent")
    public List<ManagerReportRecentResponse> reportListRecent(){
        log.info("ManagerReportRecent");

        return managerService.findRecentReport();
    }

    @GetMapping("/reportList/{page}")
    public List<ManagerReportRecentResponse> reportList(@PathVariable Integer page){
        log.info("reportList: ");

        return managerService.findReportList(page);
    }

    @GetMapping("/getTotalPage")
    public Integer getTotalPage(){
        log.info("getTotalPage");

        return managerService.getTotalPage();
    }

    @PostMapping("/{reportNo}")
    public ManagerReportReadResponse reportRead(@PathVariable Long reportNo){
        log.info("reportRead :" + reportNo);

        return managerService.readReport(reportNo);
    }

    @PostMapping("/image/{reportNo}")
    public List<String> reportReadImage(@PathVariable Long reportNo){
        log.info("reportReadImage");

        return managerService.readImage(reportNo);
    }

    @PostMapping("/board/{reportNo}")
    public ProductBoard reportReadBoard(@PathVariable Long reportNo){
        log.info("reportReadImage");

        return managerService.readBoard(reportNo);
    }

    @PostMapping("/done/{reportNo}")
    public void reportState(@PathVariable Long reportNo){
        log.info("reportReadImage");

        managerService.reportState(reportNo);
    }

    @DeleteMapping("/{reportNo}")
    public void deleteBoard(@PathVariable Long reportNo){
        log.info("deleteBoard:" +reportNo);

        managerService.deleteBoard(reportNo);
    }

    @GetMapping("/memList/{page}")
    public List<ManagerMemberListResponse> memberList(@PathVariable Integer page){
        log.info("memberList");

        return managerService.memberList(page);
    }

    @GetMapping("/member/getTotalPage")
    public Integer getMemberTotalPage(){
        log.info("getTotalPage");

        return managerService.getMemberTotalPage();
    }

    @PostMapping("/changeRole/{memberNo}")
    public void changeRole(@PathVariable Long memberNo){
        log.info("changeRole");

        managerService.changeRole(memberNo);
    }

    @PostMapping("/todayRegister")
    public Integer todayRegisterMember(){
        log.info("todayRegisterMember");

        return managerService.todayRegisterMember();
    }

    @PostMapping("/todayReport")
    public Integer todayReport(){
        log.info("todayReport");

       return managerService.todayReport();
    }
}
