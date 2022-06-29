package com.example.marketback.repository.boss.bossReview;

import com.example.marketback.entity.review.BossReview;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BossReviewRepository extends JpaRepository<BossReview, Long> {

    @Query("select br from BossReview br where br.boss.bossAuthNo = :bossNo order by br.createdDate")
    BossReview findTopByBossNoOrderByIdDesc(Long bossNo);
}
