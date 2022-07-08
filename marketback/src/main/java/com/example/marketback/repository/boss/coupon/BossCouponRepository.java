package com.example.marketback.repository.boss.coupon;

import com.example.marketback.entity.boss.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BossCouponRepository extends JpaRepository<Coupon, Long> {
    @Query("select c from Coupon c where c.boss.bossAuthNo = :bossNo")
    List<Coupon> findByBossNo(Long bossNo);

    @Query("select c from Coupon c where c.couponNo = :couponNo")
    Coupon findByCouponNo(Long couponNo);
}
