package com.example.marketback.service.boss.review;

import java.util.List;

public interface BossReviewService {
    void saveReview(List<String> fileName, String id, String name, String content, Long bossNo, String state);
}
