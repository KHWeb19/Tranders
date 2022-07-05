package com.example.marketback.repository.review;

import com.example.marketback.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r join r.member rm where rm.id = :id order by r.reviewNo desc ")
    List<Review> findReviewById(@Param("id") String id);
}
