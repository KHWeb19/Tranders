package com.example.marketback.service.review;

import com.example.marketback.entity.review.Review;

import java.util.List;

public interface ReviewService {
    public void register (Review review, String memberId, String member2Id);
    public List<Review> list (String id);
}
