package com.example.marketback.repository.boss.coupon;

import com.example.marketback.entity.boss.coupon.MemberCoupon;
import com.example.marketback.response.BossCouponMemberResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {

    @Query("select mc from MemberCoupon mc where mc.member.id = :id and mc.coupon.couponNo = :couponNo")
    Optional<MemberCoupon> findByMemberIdAndCouponNo(String id, Long couponNo);

    @Query("select mc from MemberCoupon mc where mc.coupon.boss.bossAuthNo = :bossNo")
    List<MemberCoupon> findByBossNo(Long bossNo, Pageable pageable);

    @Query("select mc from MemberCoupon mc where mc.coupon.boss.bossAuthNo = :bossNo")
    Page<MemberCoupon> findPageByBossNo(Long bossNo, Pageable pageable);

    @Query("select mc from MemberCoupon mc where mc.coupon.couponNo = :couponNo")
    List<MemberCoupon> findByCouponNo(Long couponNo);

    @Query("select mc from MemberCoupon mc where mc.member.id = :id")
    Page<MemberCoupon> findPageByMemberId(String id, Pageable pageable);

    @Query("select mc from MemberCoupon mc where mc.member.id = :id and mc.coupon.couponNo = :couponNo")
    MemberCoupon findOneByMemberIdAndCouponNo(String id, Long couponNo);
}
