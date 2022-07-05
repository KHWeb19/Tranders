package com.example.marketback.service.manager;

import com.example.marketback.entity.manager.Report;
import com.example.marketback.entity.manager.ReportImage;
import com.example.marketback.entity.manager.ReportState;
import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.member.MemberRole;
import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.repository.manager.ReportImageRepository;
import com.example.marketback.repository.manager.ReportRepository;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.repository.productBoard.ProductRepository;
import com.example.marketback.response.ManagerMemberListResponse;
import com.example.marketback.response.ManagerReportReadResponse;
import com.example.marketback.response.ManagerReportRecentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportImageRepository reportImageRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<ManagerReportRecentResponse> findRecentReport() {
        List<Report> reportList = reportRepository.findTop5ByOrderByCreateDateDesc();
        List<ManagerReportRecentResponse> response = new ArrayList<>();

        for(Report report : reportList){
            response.add(new ManagerReportRecentResponse(report.getReportNo(), report.getReportCategory1(), report.getReportCategory2(), report.getFromMember().getId(), report.getReportState().name()));
        }
        return response;
    }

    @Override
    public List<ManagerReportRecentResponse> findReportList(Integer page) {
        Pageable pageable = PageRequest.of(page,6, Sort.Direction.DESC, "createDate");
        Page<Report> reportList = reportRepository.findAll(pageable);
        reportList.getTotalPages();
        List<ManagerReportRecentResponse> response = new ArrayList<>();

        for(Report report : reportList){
            response.add(new ManagerReportRecentResponse(report.getReportNo(), report.getReportCategory1(), report.getReportCategory2(), report.getFromMember().getId(), report.getReportState().name()));
            System.out.println(report.getReportCategory1());
        }

        return response;
    }

    @Override
    public Integer getTotalPage() {
        Pageable pageable = PageRequest.of(0,6, Sort.Direction.DESC, "createDate");
        Page<Report> reportList = reportRepository.findAll(pageable);

        return reportList.getTotalPages();
    }

    @Override
    public ManagerReportReadResponse readReport(Long reportNo) {
        Report report = reportRepository.findByReportNo(reportNo);

        return new ManagerReportReadResponse(reportNo, report.getReportCategory1(), report.getReportCategory2(), report.getContent(), report.getFromMember().getId(), report.getToMember().getId(), report.getReportState().name());
    }

    @Override
    public List<String> readImage(Long reportNo) {
        List<ReportImage> reportImages = reportImageRepository.findByReportNo(reportNo);
        List<String> response = new ArrayList<>();

        if(reportImages.size() == 0 ){
            System.out.println("이미지가 없습니다");
            return null;
        }else {
            for(ReportImage reportImage : reportImages){
                response.add(reportImage.getImageName());
            }
            return response;
        }
    }

    @Override
    public ProductBoard readBoard(Long reportNo) {
        Report report = reportRepository.findByReportNo(reportNo);

        return report.getProductBoard();
    }

    @Override
    public void deleteBoard(Long reportNo) {
        Report reportEntity = reportRepository.findByReportNo(reportNo);
        List<Report> reportList = reportRepository.findReportListByProductNo(reportEntity.getProductBoard().getProductNo());
        System.out.println("repo board list" + reportList.size());

        for(Report report : reportList){
            ProductBoard productBoard = report.getProductBoard();
            report.setProductBoard(null);
            reportRepository.save(report);
            productRepository.delete(productBoard);
        }

    }

    @Override
    public void reportState(Long reportNo) {
        Report report = reportRepository.findByReportNo(reportNo);
        report.setReportState(ReportState.DONE);

        reportRepository.save(report);
    }

    @Override
    public List<ManagerMemberListResponse> memberList(Integer page) {
        Pageable pageable = PageRequest.of(page,3, Sort.Direction.DESC, "memberNo");
        Page<Member> memberList = memberRepository.findAll(pageable);

        List<ManagerMemberListResponse> responses = new ArrayList<>();

        for(Member member : memberList){
            responses.add(new ManagerMemberListResponse(member.getMemberNo(), member.getId(), member.getName(), member.getRoles().name()));
        }

        return responses;
    }

    @Override
    public Integer getMemberTotalPage() {
        Pageable pageable = PageRequest.of(0,3, Sort.Direction.DESC, "memberNo");
        Page<Member> memberList = memberRepository.findAll(pageable);

        return memberList.getTotalPages();
    }

    @Override
    public void changeRole(Long memberNo) {
        Member member = memberRepository.findByMemberNo(memberNo);
        member.setRoles(MemberRole.ROLE_USER);

        memberRepository.save(member);
    }

    @Override
    public Integer todayRegisterMember() {
        LocalDate now = LocalDate.now();
        List<Member> member= memberRepository.findByCreateDateStartingWith(now.toString());

        return member.size();
    }

    @Override
    public Integer todayReport() {
        LocalDate now = LocalDate.now();
        List<Report> reportList = reportRepository.findByCreateDateStartingWith(now.toString());
        return reportList.size();
    }
}