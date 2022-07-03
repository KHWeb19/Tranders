package com.example.marketback.service.boss.review;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.near.Near;
import com.example.marketback.entity.review.BossReview;
import com.example.marketback.entity.review.BossReviewImage;
import com.example.marketback.repository.boss.BossRepository;
import com.example.marketback.repository.boss.bossReview.BossReviewImageRepository;
import com.example.marketback.repository.boss.bossReview.BossReviewRepository;
import com.example.marketback.repository.jpa.community.CommunityBoardRepository;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.repository.near.NearRepository;
import com.example.marketback.response.CommunityBoardListResponse;
import com.example.marketback.response.ReviewResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class BossReviewServiceImpl implements BossReviewService{

    @Autowired
    private BossReviewRepository bossReviewRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BossRepository bossRepository;

    @Autowired
    private BossReviewImageRepository bossReviewImageRepository;

    @Autowired
    private CommunityBoardRepository communityBoardRepository;

    @Autowired
    private NearRepository nearRepository;

    @Override
    public void saveReview(List<String> fileName, String id, String name, String content, Long bossNo, Long nearNo, String state) {
        Member memberEntity = memberRepository.findByMemberId(id);

        BossReview reviewEntity;

        if(nearNo == 0) {
            Boss bossEntity = bossRepository.findByBossNo(bossNo);
            bossEntity.setReviewCount(bossEntity.getReviewCount() +1);
            reviewEntity = new BossReview(content, state, memberEntity, bossEntity, null);
        }else {
            Near nearEntity = nearRepository.findByNearNo(nearNo);
            nearEntity.setReviewCount(nearEntity.getReviewCount() +1);
            reviewEntity = new BossReview(content, state, memberEntity,  null, nearEntity);
        }

        for(String imgName : fileName){
            BossReviewImage bri = new BossReviewImage(imgName, reviewEntity);
            bossReviewImageRepository.save(bri);
        }

        bossReviewRepository.save(reviewEntity);
    }

    @Override
    public List<ReviewResponse> getReview(String num) {
        List<BossReview> bossReviewEntity;

        if(num.charAt(0) == '0' && num.charAt(1) == '0') {
            bossReviewEntity = bossReviewRepository.findByNearNo(Long.valueOf(num));
        }else{
            bossReviewEntity = bossReviewRepository.findByBossNo(Long.valueOf(num));
        }

        List<ReviewResponse> response = new ArrayList<>();

        if(bossReviewEntity.size() == 0){
            return null;
        }else {
            for (BossReview bossReview : bossReviewEntity) {
                //log.info(bossReview.getBossReviewImagesList().get(0).getImageName() + "이미지");
                response.add(new ReviewResponse(bossReview.getBossReviewNo(), bossReview.getMember().getName(), bossReview.getMember().getRegion(), bossReview.getContent(), bossReview.getMember().getProfileImg(), bossReview.getState(), bossReview.getCreatedDate()));
            }
            return response;
        }
    }

    @Override
    public List<List<String>> getReviewImg(List<BossReview> bossReviewEntity, String num) {
        List<List<String>> bossReviewImageList = new ArrayList<>();

        if(num.charAt(0) == '0' && num.charAt(1) == '0') {
            for (BossReview review : bossReviewEntity) {
                log.info(review.getBossReviewNo().toString());

                List<BossReviewImage> BossReviewImageList = bossReviewImageRepository.findByNearNo(Long.valueOf(num), review.getBossReviewNo());
                List<String> name = new ArrayList<>();
                for (BossReviewImage bossReviewImage : BossReviewImageList) {
                    name.add(bossReviewImage.getImageName());
                    System.out.println(bossReviewImage.getImageName());
                }
                bossReviewImageList.add(name);
                System.out.println(bossReviewImageList.get(0));
            }

        }else if(num.charAt(0) == '0') {
            for (BossReview review : bossReviewEntity) {
                log.info(review.getBossReviewNo() + "no");

                List<BossReviewImage> BossReviewImageList = bossReviewImageRepository.findByBossNo(Long.valueOf(num), review.getBossReviewNo());
                List<String> name = new ArrayList<>();
                for (BossReviewImage bossReviewImage : BossReviewImageList) {
                    name.add(bossReviewImage.getImageName());
                }
                bossReviewImageList.add(name);
            }
        }

        return bossReviewImageList;
    }

    @Override
    public List<CommunityBoardListResponse> getComm(String num) {
        List<CommunityBoard> boardEntity;

        if(num.charAt(0) == '0' && num.charAt(1) == '0') {
            boardEntity = communityBoardRepository.findByNearNo(Long.valueOf(num));
        }else {
            boardEntity = communityBoardRepository.findByBossNo(Long.valueOf(num));
        }

        List<CommunityBoardListResponse> responses = new ArrayList<>();

        for(CommunityBoard board : boardEntity){
            responses.add(new CommunityBoardListResponse(board.getBoardNo(), board.getUsedSubject(), board.getTitle(), board.getWriter(), board.getRegion(), board.getContent(), board.getMember().getProfileImg(), board.getFileName1(), board.getCreatedDate()));
        }

        return responses;
    }
}
