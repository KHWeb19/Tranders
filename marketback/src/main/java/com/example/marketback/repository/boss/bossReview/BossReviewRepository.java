package com.example.marketback.repository.boss.bossReview;

import com.example.marketback.entity.boss.Boss;
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

    @Query("select br from BossReview br where br.near.nearNo = :nearNo")
    List<BossReview> findByNearNo(Long nearNo);

    @Query("select br from BossReview br where br.boss.bossAuthNo = :bossNo")
    BossReview findBossReviewByBossNo(Long bossNo);

    @Query("select br from BossReview br where br.near.nearNo = :nearNo")
    BossReview findBossReviewByNearNo(Long nearNo);

    @Query("select br from BossReview br where br.bossReviewNo = :reviewNo")
    BossReview findByReviewNo(Long reviewNo);

    @Query("select br from BossReview br where br.boss.bossAuthNo = :bossAuthNo order by br.createdDate")
    List<BossReview> indTopByBossNoOrderByIdAsc(Long bossAuthNo);

    @Query("select br from BossReview br where br.near.nearNo = :nearNo order by br.createdDate")
    List<BossReview> findByNearNoOrderByCreatedDateDesc(Long nearNo);

    @Query("select br from BossReview br where br.boss.bossAuthNo = :bossNo order by br.createdDate")
    List<BossReview> findByBossNoOrderByCreatedDateAsc(Long bossNo);

    @Query("select br from BossReview br where br.boss.bossAuthNo = :bossNo order by br.createdDate")
    List<BossReview> findByBossAuthNoOrderByCreatedDateAsc(Long bossNo);
//    @Query("select br from BossReview br where br.boss.bossAuthNo = :bossNo order by br.createdDate")
//    List<BossReview> findTop1ByBossNoOrderByIdDesc(Long bossAuthNo);
}
