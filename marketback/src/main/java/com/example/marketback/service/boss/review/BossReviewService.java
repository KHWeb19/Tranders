package com.example.marketback.service.boss.review;

import com.example.marketback.entity.review.BossReview;
import com.example.marketback.response.BossReviewImageResponse;
import com.example.marketback.response.CommunityBoardListResponse;
import com.example.marketback.response.ReviewResponse;

import java.util.List;

public interface BossReviewService {
    void saveReview(List<String> fileName, String id, String name, String content, Long bossNo, Long nearNo, String state);

    void saveNoImgReview(String id, String name, String content, Long bossNo, Long nearNo, String state);

    List<ReviewResponse> getReview(String bossNo);

    List<List<BossReviewImageResponse>> getReviewImg(List<BossReview> bossReviewEntity, String no);

    List<CommunityBoardListResponse> getComm(String bossNo);

    void modifyImgReview(List<String> fileName, String id, String content, Long reviewNo, String state, List<String> deleteImg);

    void modifyReview(String content, String state, Long reviewNo, List<String> deleteImg);

    void deleteReview(Long reviewNo);

}
