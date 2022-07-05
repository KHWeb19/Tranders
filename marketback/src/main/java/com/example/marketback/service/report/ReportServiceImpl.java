package com.example.marketback.service.report;

import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.manager.BanMember;
import com.example.marketback.entity.manager.Report;
import com.example.marketback.entity.manager.ReportImage;
import com.example.marketback.entity.manager.ReportState;
import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.repository.jpa.community.CommunityBoardRepository;
import com.example.marketback.repository.manager.BanMemberRepository;
import com.example.marketback.repository.manager.ReportImageRepository;
import com.example.marketback.repository.manager.ReportRepository;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.repository.productBoard.ProductRepository;
import com.example.marketback.request.ReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CommunityBoardRepository communityBoardRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportImageRepository reportImageRepository;

    @Autowired
    private BanMemberRepository banMemberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveReport(ReportRequest reportRequest) {
        Member toMember = memberRepository.findByMemberId(reportRequest.getToId());
        Member fromMember = memberRepository.findByMemberId(reportRequest.getFromId());
        ProductBoard product = productRepository.findByProductNo(reportRequest.getProductNo());

        Report report = new Report(reportRequest.getCategory1(), reportRequest.getCategory2(), reportRequest.getContent(), reportRequest.getBanCheck(), toMember, fromMember, product);
        report.setReportState(ReportState.PROCESSING);

        if(report.getBanCheck() == Boolean.TRUE){
            BanMember banMember = new BanMember(toMember, fromMember);
            banMemberRepository.save(banMember);
        }

        reportRepository.save(report);
    }

    @Override
    public void saveReportImg(List<String> fileName, ReportRequest reportRequest) {
        Member toMember = memberRepository.findByMemberId(reportRequest.getToId());
        Member fromMember = memberRepository.findByMemberId(reportRequest.getFromId());
        ProductBoard product = productRepository.findByProductNo(reportRequest.getProductNo());

        Report report = new Report(reportRequest.getCategory1(), reportRequest.getCategory2(), reportRequest.getContent(), reportRequest.getBanCheck(), toMember, fromMember, product);
        report.setReportState(ReportState.PROCESSING);

        reportRepository.save(report);

        if(report.getBanCheck() == Boolean.TRUE){
            BanMember banMember = new BanMember(toMember, fromMember);
            banMemberRepository.save(banMember);
        }

        for(String img : fileName){
            ReportImage image = new ReportImage(img, report);
           reportImageRepository.save(image);
        }
    }
}
