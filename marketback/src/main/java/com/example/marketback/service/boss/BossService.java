package com.example.marketback.service.boss;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.boss.BossPrice;
import com.example.marketback.entity.boss.coupon.Coupon;
import com.example.marketback.request.BossCouponRequest;
import com.example.marketback.request.BossMarketInfoRequest;
import com.example.marketback.request.MemberCouponRequest;
import com.example.marketback.response.BossBackProfileImg;
import com.example.marketback.response.BossCouponMemberResponse;
import com.example.marketback.response.BossCouponResponse;
import com.example.marketback.response.BossPriceMenuResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BossService {
    boolean checkBossMember(String id);

    void register(String fileSrc, String id, String name, String region, String category);

    Boss getPage(Long memberNo);

    void saveMarketInfo(BossMarketInfoRequest bossMarketInfoRequest);

    void saveBackImg(String id, String name, List<String> fileName);

    void modifyProfile(String fileSrc, String id);

    List<BossBackProfileImg> getBackProfile(Long id);

    void addPrice(BossPrice bossPrice, Long bossNo);

    List<BossPriceMenuResponse> getMenu(String id);

    void modifyMenu(BossPrice bossPrice);

    void deleteMenu(Long menuNo);

    void addCoupon(BossCouponRequest bossCouponRequest);

    List<BossCouponResponse> showCoupon(Long bossNo);

    void modifyCoupon(Coupon coupon);

    ResponseEntity<Boolean> getCoupon(MemberCouponRequest memberCouponRequest);

    List<BossCouponMemberResponse> couponMember(Long bossNo, Integer page);

    Integer getTotalPage(Long bossNo);

    void deleteCoupon(Long couponNo);
}
