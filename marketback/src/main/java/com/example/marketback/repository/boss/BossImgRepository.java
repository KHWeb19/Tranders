package com.example.marketback.repository.boss;

import com.example.marketback.entity.boss.BossImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BossImgRepository extends JpaRepository<BossImage, Long> {

    @Query("select bi from BossImage bi where bi.boss.member.id = :id")
    BossImage findByMemberId(String id);

    @Query("select bi from BossImage bi where bi.boss.member.id = :id")
    List<BossImage> findImgListByMemberId(String id);

    @Query("select bi from BossImage bi where bi.boss.bossAuthNo = :id")
    List<BossImage> findImgListByBossNo(Long id);
}
