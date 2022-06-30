package com.example.marketback.service.boss.review;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.review.BossReview;
import com.example.marketback.entity.review.BossReviewImage;
import com.example.marketback.repository.boss.BossRepository;
import com.example.marketback.repository.boss.bossReview.BossReviewImageRepository;
import com.example.marketback.repository.boss.bossReview.BossReviewRepository;
import com.example.marketback.repository.member.MemberRepository;
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

    @Override
    public void saveReview(List<String> fileName, String id, String name, String content, Long bossNo, String state) {
        Member memberEntity = memberRepository.findByMemberId(id);
        Boss bossEntity = bossRepository.findByBossNo(bossNo);
        bossEntity.setReviewCount(bossEntity.getReviewCount() +1);

        BossReview reviewEntity = new BossReview(content, state, memberEntity, bossEntity);

        for(String imgName : fileName){
            BossReviewImage bri = new BossReviewImage(imgName, reviewEntity);
            bossReviewImageRepository.save(bri);
        }

        bossReviewRepository.save(reviewEntity);
    }

    @Override
    public List<ReviewResponse> getReview(Long bossNo) {
        List<BossReview> bossReviewEntity = bossReviewRepository.findByBossNo(bossNo);

        List<ReviewResponse> response = new ArrayList<>();

        for (BossReview bossReview : bossReviewEntity){
            log.info(bossReview.getBossReviewImagesList().get(0).getImageName()+ "이미지");
            response.add(new ReviewResponse(bossReview.getBossReviewNo(), bossReview.getMember().getName(), bossReview.getMember().getRegion(), bossReview.getContent(), bossReview.getMember().getProfileImg(), bossReview.getState()));
        }

        return response;
    }

    @Override
    public List<List<String>> getReviewImg(List<BossReview> bossReviewEntity, Long bossNo) {
        List<List<String>> bossReviewImageList = new ArrayList<>();
        //log.info("size: " + bossReviewEntity.size());

        for (BossReview review : bossReviewEntity) {
            log.info(review.getBossReviewNo()+"no");
            List<BossReviewImage> BossReviewImageList = bossReviewImageRepository.findByBossNo(bossNo, review.getBossReviewNo());
            List<String> name = new ArrayList<>();
            for(BossReviewImage bossReviewImage : BossReviewImageList){
                name.add(bossReviewImage.getImageName());
            }
            bossReviewImageList.add(name);
        }


        return bossReviewImageList;
    }
}
