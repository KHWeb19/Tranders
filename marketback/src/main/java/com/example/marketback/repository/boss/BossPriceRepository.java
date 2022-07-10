package com.example.marketback.repository.boss;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.boss.BossPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BossPriceRepository extends JpaRepository<BossPrice, Long> {

    @Query("select bp from BossPrice bp where bp.boss.member.id = :id")
    List<BossPrice> findByMemberId(String id);

    @Query("select bp from BossPrice bp where bp.bossPriceNo = :bossPriceNo")
    BossPrice findBossByBossId(Long bossPriceNo);

    @Query("select bp from BossPrice bp where bp.boss.bossAuthNo = :bossNo")
    List<BossPrice> findByBossNo(Long bossNo);
}
