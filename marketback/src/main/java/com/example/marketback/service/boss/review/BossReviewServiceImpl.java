package com.example.marketback.service.boss.review;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.boss.BossImage;
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
import com.example.marketback.response.BossReviewImageResponse;
import com.example.marketback.response.CommunityBoardListResponse;
import com.example.marketback.response.ReviewResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public void saveNoImgReview(String id, String name, String content, Long bossNo, Long nearNo, String state) {
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

        bossReviewRepository.save(reviewEntity);
    }

    @Override
    public List<ReviewResponse> getReview(String num) {
        List<BossReview> bossReviewEntity = null;

        if(num.charAt(0) == '0' && num.charAt(1) == '0') {//indTop3ByNameOrderByIdDesc
            bossReviewEntity = bossReviewRepository.findByNearNoOrderByDateDesc(Long.valueOf(num), Sort.by(Sort.Order.desc("createDate")));
        }else if (num.charAt(0) == '0'){
            bossReviewEntity = bossReviewRepository.findByBossAuthNoOrderByIdDesc(Long.valueOf(num), Sort.by(Sort.Order.desc("createDate")));
        }

        List<ReviewResponse> response = new ArrayList<>();

        if(bossReviewEntity.size() != 0) {
            for (BossReview bossReview : bossReviewEntity) {
                String date = bossReview.getCreateDate();
                response.add(new ReviewResponse(bossReview.getBossReviewNo(), bossReview.getMember().getId(), bossReview.getMember().getName(), bossReview.getMember().getRegion(), bossReview.getContent(), bossReview.getMember().getProfileImg(), bossReview.getState(), date));
            }
            return response;
        }else {
            return null;
        }
    }

    @Override
    public List<List<BossReviewImageResponse>> getReviewImg(List<BossReview> bossReviewEntity, String num) {
        List<List<BossReviewImageResponse>> bossReviewImageList = new ArrayList<>();

        if(num.charAt(0) == '0' && num.charAt(1) == '0') {
            for (BossReview review : bossReviewEntity) {
                log.info(review.getBossReviewNo().toString());

                List<BossReviewImage> BossReviewImageList = bossReviewImageRepository.findBySortNearNo(Long.valueOf(num), review.getBossReviewNo(), Sort.by(Sort.Order.desc("createDate")));
                List<BossReviewImageResponse> name = new ArrayList<>();

                if(BossReviewImageList.size() != 0) {
                    for (BossReviewImage bossReviewImage : BossReviewImageList) {
                        name.add(new BossReviewImageResponse(bossReviewImage.getImageName(), bossReviewImage.getReviewImageNo()));
                        System.out.println(bossReviewImage.getImageName());
                    }
                }else {
                    name.add(null);
                }

                bossReviewImageList.add(name);
            }

        }else if(num.charAt(0) == '0') {
            for (BossReview review : bossReviewEntity) {
                log.info(review.getBossReviewNo() + "no");

                List<BossReviewImage> BossReviewImageList = bossReviewImageRepository.findBySortBossNo(Long.valueOf(num), review.getBossReviewNo(), Sort.by(Sort.Order.desc("createDate")));
                List<BossReviewImageResponse> name = new ArrayList<>();

                if(BossReviewImageList.size() != 0) {
                    for (BossReviewImage bossReviewImage : BossReviewImageList) {
                        name.add(new BossReviewImageResponse(bossReviewImage.getImageName(), bossReviewImage.getReviewImageNo()));
                    }
                }else {
                    name.add(null);
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

        if(boardEntity.size() != 0){

            for(CommunityBoard board : boardEntity){
                responses.add(new CommunityBoardListResponse(board.getBoardNo(), board.getUsedSubject(), board.getTitle(), board.getWriter(), board.getRegion(), board.getContent(), board.getMember().getProfileImg(), board.getFileName1(), board.getCreatedDate()));
            }

            return responses;
        }else {
            return null;
        }
    }

    @Override
    public void modifyImgReview(List<String> fileName, String id, String content, Long reviewNo, String state, List<String> deleteImg) {
        BossReview reviewEntity = bossReviewRepository.findByReviewNo(reviewNo);

        reviewEntity.setContent(content);
        reviewEntity.setState(state);

        if(!Objects.equals(deleteImg.get(0), "noImg")) {
            for (String number : deleteImg) {
                System.out.println(number);

                BossReviewImage bossReviewImage = bossReviewImageRepository.findByReviewImageNo(Long.valueOf(number));
                bossReviewImageRepository.delete(bossReviewImage);
            }
        }

        for(String imgName : fileName){
            BossReviewImage bri = new BossReviewImage(imgName, reviewEntity);
            bossReviewImageRepository.save(bri);
        }

        bossReviewRepository.save(reviewEntity);
    }

     @Override
    public void modifyReview(String content, String state, Long reviewNo, List<String> deleteImg) {
        BossReview reviewEntity = bossReviewRepository.findByReviewNo(reviewNo);

        reviewEntity.setContent(content);
        reviewEntity.setState(state);

        if(!Objects.equals(deleteImg.get(0), "noImg")) {
            for (String number : deleteImg) {
                System.out.println(number);

                BossReviewImage bossReviewImage = bossReviewImageRepository.findByReviewImageNo(Long.valueOf(number));
                bossReviewImageRepository.delete(bossReviewImage);
            }
        }

        bossReviewRepository.save(reviewEntity);
    }

    @Override
    public void deleteReview(Long reviewNo, String num) {
        List<BossReviewImage> bossReviewImages = bossReviewImageRepository.findByReviewNo(reviewNo);
        bossReviewImageRepository.deleteAll(bossReviewImages);
        bossReviewRepository.deleteById(reviewNo);

        if(num.charAt(0) == '0' && num.charAt(1) == '0') {
            Boss boss = bossRepository.findByBossNo(Long.valueOf(num));
            boss.setReviewCount(boss.getReviewCount() -1);
        }else {
            Near near = nearRepository.findByNearNo(Long.valueOf(num));
            near.setReviewCount(near.getReviewCount() -1);
        }
    }
}
