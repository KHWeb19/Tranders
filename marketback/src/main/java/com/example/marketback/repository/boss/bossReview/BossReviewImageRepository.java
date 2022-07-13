package com.example.marketback.repository.boss.bossReview;

import com.example.marketback.entity.review.BossReviewImage;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BossReviewImageRepository extends JpaRepository<BossReviewImage, Long> {
    @Query("select bri from BossReviewImage bri where bri.review.boss.bossAuthNo = :bossNo and bri.review.bossReviewNo = :bossReviewNo")
    List<BossReviewImage> findByBossNo(Long bossNo, Long bossReviewNo);

    @Query("select bri from BossReviewImage bri where bri.review.near.nearNo = :nearNo and bri.review.bossReviewNo = :bossReviewNo")
    List<BossReviewImage> findByNearNo(Long nearNo, Long bossReviewNo);

    @Query("select bri from BossReviewImage bri where bri.reviewImageNo = :number")
    BossReviewImage findByReviewImageNo(Long number);

    @Query("select bri from BossReviewImage bri where bri.review.bossReviewNo = :reviewNo")
    List<BossReviewImage> findByReviewNo(Long reviewNo);

    @Query("select bri from BossReviewImage bri where bri.review.boss.bossAuthNo = :bossAuthNo")
    List<BossReviewImage> findRemoveByBossNo(Long bossAuthNo);

    @Query("select bri from BossReviewImage bri where bri.review.near.nearNo = :nearNo and bri.review.bossReviewNo = :bossReviewNo")
    List<BossReviewImage> findBySortNearNo(Long nearNo, Long bossReviewNo, Sort createDate);
    @Query("select bri from BossReviewImage bri where bri.review.boss.bossAuthNo = :bossNo and bri.review.bossReviewNo = :bossReviewNo")
    List<BossReviewImage> findBySortBossNo(Long bossNo, Long bossReviewNo, Sort createDate);

    @Query("select bri from BossReviewImage bri where bri.review.member.id = :id")
    List<BossReviewImage> findByMemberId(String id);
}
