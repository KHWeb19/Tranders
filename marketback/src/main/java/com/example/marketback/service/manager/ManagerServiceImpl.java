package com.example.marketback.service.manager;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.boss.BossImage;
import com.example.marketback.entity.boss.BossPrice;
import com.example.marketback.entity.boss.coupon.Coupon;
import com.example.marketback.entity.boss.coupon.MemberCoupon;
import com.example.marketback.entity.chatting.ChatRoom;
import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.jpa.community.CommunityBoardLike;
import com.example.marketback.entity.jpa.community.CommunityComment;
import com.example.marketback.entity.manager.BanMember;
import com.example.marketback.entity.manager.Report;
import com.example.marketback.entity.manager.ReportImage;
import com.example.marketback.entity.manager.ReportState;
import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.member.MemberRole;
import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.entity.productBoard.ProductLikes;
import com.example.marketback.entity.review.BossReview;
import com.example.marketback.entity.review.BossReviewImage;
import com.example.marketback.entity.review.Manner;
import com.example.marketback.entity.review.Review;
import com.example.marketback.repository.boss.BossImgRepository;
import com.example.marketback.repository.boss.BossPriceRepository;
import com.example.marketback.repository.boss.BossRepository;
import com.example.marketback.repository.boss.bossReview.BossReviewImageRepository;
import com.example.marketback.repository.boss.bossReview.BossReviewRepository;
import com.example.marketback.repository.boss.coupon.BossCouponRepository;
import com.example.marketback.repository.boss.coupon.MemberCouponRepository;
import com.example.marketback.repository.chatting.ChatRoomRepository;
import com.example.marketback.repository.jpa.community.CommunityBoardLikeRepository;
import com.example.marketback.repository.jpa.community.CommunityBoardRepository;
import com.example.marketback.repository.jpa.community.CommunityCommentRepository;
import com.example.marketback.repository.manager.BanMemberRepository;
import com.example.marketback.repository.manager.ReportImageRepository;
import com.example.marketback.repository.manager.ReportRepository;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.repository.productBoard.ProductLikesRepository;
import com.example.marketback.repository.productBoard.ProductRepository;
import com.example.marketback.repository.review.MannerRepository;
import com.example.marketback.repository.review.ReviewRepository;
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
import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportImageRepository reportImageRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductLikesRepository productLikesRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BossRepository bossRepository;

    @Autowired
    private BossImgRepository bossImgRepository;

    @Autowired
    private BossPriceRepository bossPriceRepository;

    @Autowired
    private MemberCouponRepository memberCouponRepository;

    @Autowired
    private BossCouponRepository bossCouponRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private CommunityBoardRepository communityBoardRepository;

    @Autowired
    private CommunityCommentRepository communityCommentRepository;

    @Autowired
    private CommunityBoardLikeRepository communityBoardLikeRepository;

    @Autowired
    private BossReviewImageRepository bossReviewImageRepository;

    @Autowired
    private BossReviewRepository bossReviewRepository;

    @Autowired
    private MannerRepository mannerRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BanMemberRepository banMemberRepository;
    @Override
    public List<ManagerReportRecentResponse> findRecentReport() {
        List<Report> reportList = reportRepository.findTop5ByOrderByCreateDateDesc();
        List<ManagerReportRecentResponse> response = new ArrayList<>();

        for(Report report : reportList){
            response.add(new ManagerReportRecentResponse(report.getReportNo(), report.getReportCategory1(), report.getReportCategory2(), report.getFromMember() == null ? null : report.getFromMember().getId(), report.getReportState().name()));
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
            response.add(new ManagerReportRecentResponse(report.getReportNo(), report.getReportCategory1(), report.getReportCategory2(), report.getFromMember() == null ? null : report.getFromMember().getId(), report.getReportState().name()));
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

        return new ManagerReportReadResponse(reportNo, report.getReportCategory1(), report.getReportCategory2(), report.getContent(), report.getToMember() == null ? null : report.getToMember().getId(), report.getFromMember() == null ? null : report.getFromMember().getId(), report.getReportState().name());
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
            report.setProductBoard(null);
            reportRepository.save(report);
        }

        ProductBoard productBoard = reportEntity.getProductBoard();

        productRepository.delete(productBoard);

    }

    @Override
    public void reportState(Long reportNo) {
        Report report = reportRepository.findByReportNo(reportNo);
        report.setReportState(ReportState.DONE);

        reportRepository.save(report);
    }

    @Override
    public List<ManagerMemberListResponse> memberList(Integer page) {
        Pageable pageable = PageRequest.of(page,10, Sort.Direction.DESC, "memberNo");
        Page<Member> memberList = memberRepository.findAll(pageable);

        List<ManagerMemberListResponse> responses = new ArrayList<>();

        for(Member member : memberList){
            responses.add(new ManagerMemberListResponse(member.getMemberNo(), member.getId(), member.getName(), member.getRoles().name()));
        }

        return responses;
    }

    @Override
    public Integer getMemberTotalPage() {
        Pageable pageable = PageRequest.of(0,10, Sort.Direction.DESC, "memberNo");
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

    @Override
    public void removeMember(String id, Integer num) {
        Member member = memberRepository.findByMemberId(id);

        Optional<Boss> ifBoss = bossRepository.findOptByMemberId(id);
        System.out.println("여기까지 들어와!?");

        List<BossReviewImage> bossReviewImageList = bossReviewImageRepository.findByMemberId(member.getId());
        List<BossReview> bossReviewList = bossReviewRepository.findByMemberId(member.getId());

        if(bossReviewImageList.size() != 0 ){
            bossReviewImageRepository.deleteAll(bossReviewImageList);
        }

        if(bossReviewList.size() != 0 ){
            bossReviewRepository.deleteAll(bossReviewList);
        }

        if(ifBoss.isPresent()) {
            List<BossImage> bossImages = bossImgRepository.findImgListByMemberId(id);
            List<BossPrice> bossPrice = bossPriceRepository.findByMemberId(id);

            if (bossImages.size() != 0) {
                bossImgRepository.deleteAll(bossImages);
            }

            if (bossImages.size() != 0) {
                bossPriceRepository.deleteAll(bossPrice);
            }

            List<Coupon> coupons = bossCouponRepository.findByBossNo(ifBoss.get().getBossAuthNo());

            List<MemberCoupon> memberCouponList = memberCouponRepository.findRemoveByBossNo(ifBoss.get().getBossAuthNo());

            if(memberCouponList.size() != 0){
                memberCouponRepository.deleteAll(memberCouponList);
            }

            if(coupons.size() != 0){
                bossCouponRepository.deleteAll(coupons);
            }

            List<BossReviewImage> bossReviewImages = bossReviewImageRepository.findRemoveByBossNo(ifBoss.get().getBossAuthNo());
            List<BossReview> bossReviews = bossReviewRepository.findByBossNo(ifBoss.get().getBossAuthNo());

            if(bossReviewImages.size() != 0 ){
                bossReviewImageRepository.deleteAll(bossReviewImages);
            }

            if(bossReviews.size() != 0 ){
                bossReviewRepository.deleteAll(bossReviews);
            }

            ifBoss.ifPresent(boss -> bossRepository.delete(boss));
        }

        List<ChatRoom> chatRoom = chatRoomRepository.findByMemberId(id);
        if(chatRoom.size() != 0) {
            chatRoomRepository.deleteAll(chatRoom);
        }

        List<CommunityBoardLike> boardLikes = communityBoardLikeRepository.findByMemberId(id);
        List<CommunityComment> boardComment = communityCommentRepository.findByMemberId(id);
        List<CommunityBoard> boards = communityBoardRepository.findByMemberId(id);

        if(boardLikes.size() != 0 ){
            communityBoardLikeRepository.deleteAll(boardLikes);
        }

        if(boardComment.size() != 0 ){
            communityCommentRepository.deleteAll(boardComment);
        }

        if(boards.size() != 0 ){
            communityBoardRepository.deleteAll(boards);
        }

        System.out.println("여기까지 들어와!?3");

        System.out.println("여기까지 들어와!?4");
        List<MemberCoupon> memberCouponList = memberCouponRepository.findRemoveByMemberId(id);
        System.out.println("여기까지 들어와!?5");
        if(memberCouponList.size() != 0 ) {
            memberCouponRepository.deleteAll(memberCouponList);
        }

        System.out.println("여기까지 들어와!?6");
        List<Manner> manners = mannerRepository.findRemoveByMemberId(id);

        if(manners.size() != 0 ) {
            mannerRepository.deleteAll(manners);
        }

        System.out.println("여기까지 들어와!?7");
        List<Review> reviews = reviewRepository.findRemoveMember(id);

        if(reviews.size() != 0 ) {
            reviewRepository.deleteAll(reviews);
        }

        List<ReportImage> reportImages = reportImageRepository.findByMemberId(id);
        if(reportImages.size() != 0 ){
            reportImageRepository.deleteAll(reportImages);
        }

        List<Report> toReportList = reportRepository.findByToMemberId(id);
        for(Report report : toReportList){
            report.setToMember(null);
            reportRepository.save(report);
        }

        List<Report> fromReportList = reportRepository.findByFromMemberId(id);
        for(Report report : fromReportList){
            report.setFromMember(null);
            reportRepository.save(report);
        }

        List<ProductBoard> productBoards = productRepository.findByMemberId(id);

        System.out.println("여기까지 들어와!?31");
        for (ProductBoard productBoard : productBoards) { // 내 상품에 좋아요한 사람 삭제
            System.out.println("여기까지 들어와!?312");
            List<ProductLikes> productLikes = productLikesRepository.findRemoveByProductNo(productBoard.getProductNo());
            List<Report> productReportList = reportRepository.findReportListByProductNo(productBoard.getProductNo());
            System.out.println("여기까지 들어와!?32");

            if(productLikes.size() != 0) {
                productLikesRepository.deleteAll(productLikes);
            }

            if(productReportList.size() != 0) {
                reportRepository.deleteAll(productReportList);
            }
        }

        System.out.println("여기까지 들어와!?4123");

        List<ProductLikes> productLikes = productLikesRepository.findByMemberId(id);
        if(productLikes.size() != 0) {
            productLikesRepository.deleteAll(productLikes); // 내가 좋아요한거 삭제
        }

        System.out.println("여기까지 들어와!?4123");
        if(productBoards.size() != 0 ){
            productRepository.deleteAll(productBoards);
        }

        System.out.println("여기까지 들어와!?8");

        List<BanMember> banMembers = banMemberRepository.findByMemberId(id);
        if(banMembers.size() != 0 ){
            banMemberRepository.deleteAll(banMembers);
        }

        System.out.println("여기까지 들어와!?9");
        memberRepository.delete(member);

        if(num == 0) {
            // 신고한 게시물이 같은건 다 상태를 변경하자
            for (Report report : toReportList) {
                report.setReportState(ReportState.DONE);
                reportRepository.save(report);
            }
        }
    }
}