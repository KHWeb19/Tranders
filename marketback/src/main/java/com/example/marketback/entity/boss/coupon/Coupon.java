package com.example.marketback.entity.boss.coupon;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUPON_NO")
    private Long couponNo;

    private String couponName;

    private String couponDate;

    private Integer couponMax;

    @Lob
    private String couponInfo;

    private Integer giveCoupon;

    @ManyToOne
    @JoinColumn(name = "BOSS_AUTH_NO")
    private Boss boss;

    public Coupon(String couponName, String couponDate, Integer couponMax, String couponInfo, Integer giveCoupon, Boss boss) {
        this.couponName = couponName;
        this.couponDate = couponDate;
        this.couponMax = couponMax;
        this.couponInfo = couponInfo;
        this.giveCoupon = giveCoupon;
        this.boss = boss;
    }
}
