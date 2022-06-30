package com.example.marketback.repository.boss.bossReview;

import com.example.marketback.entity.review.BossReview;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BossReviewRepository extends JpaRepository<BossReview, Long> {

    @Query("select br from BossReview br where br.boss.bossAuthNo = :bossNo order by br.createdDate")
    List<BossReview> findTopByBossNoOrderByIdDesc(Long bossNo);

    @Query("select br from BossReview br where br.boss.bossAuthNo = :bossNo")
    List<BossReview> findByBossNo(Long bossNo);

//    @Query("select br from BossReview br where br.boss.bossAuthNo = :bossNo order by br.createdDate")
//    List<BossReview> findTop1ByBossNoOrderByIdDesc(Long bossAuthNo);
}
