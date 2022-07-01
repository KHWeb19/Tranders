package com.example.marketback.service.boss.review;

import com.example.marketback.entity.review.BossReview;
import com.example.marketback.response.CommunityBoardListResponse;
import com.example.marketback.response.ReviewResponse;

import java.util.List;

public interface BossReviewService {
    void saveReview(List<String> fileName, String id, String name, String content, Long bossNo, Long nearNo, String state);

    List<ReviewResponse> getReview(String bossNo);

    List<List<String>> getReviewImg(List<BossReview> bossReviewEntity, String no);

    List<CommunityBoardListResponse> getComm(String bossNo);
}
