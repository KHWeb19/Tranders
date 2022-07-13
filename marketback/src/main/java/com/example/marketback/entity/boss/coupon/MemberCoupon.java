package com.example.marketback.entity.boss.coupon;

import com.example.marketback.entity.member.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_COUPON_NO")
    private Long MemberCouponNo;

    @ManyToOne
    @JoinColumn(name = "COUPON_NO")
    private Coupon coupon;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @Enumerated(EnumType.STRING)
    private CouponState couponState;

    @CreatedDate
    private String createDate = String.valueOf(LocalDateTime.now());

    public MemberCoupon(Coupon coupon, Member member) {
        this.coupon = coupon;
        this.member = member;
        this.couponState = CouponState.BEFORE_USE;
    }
}
