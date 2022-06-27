package com.example.marketback.repository.boss;

import com.example.marketback.entity.boss.Boss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BossRepository extends JpaRepository<Boss, Long> {
    @Query("select b from Boss b where b.member.memberNo = :memberNo")
    Boss findByMemberNo(Long memberNo);

    @Query("select b from Boss b where b.member.id = :id")
    Boss findByMemberId(String id);

    @Query("select b from Boss b where b.bossAuthNo = :bossNo")
    Boss findByBossNo(Long bossNo);
}
