package com.example.marketback.service.boss;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.boss.BossImage;
import com.example.marketback.entity.boss.BossPrice;
import com.example.marketback.entity.boss.coupon.Coupon;
import com.example.marketback.entity.boss.coupon.MemberCoupon;
import com.example.marketback.entity.manager.Report;
import com.example.marketback.entity.member.Member;
import com.example.marketback.repository.boss.BossImgRepository;
import com.example.marketback.repository.boss.BossPriceRepository;
import com.example.marketback.repository.boss.BossRepository;
import com.example.marketback.repository.boss.coupon.BossCouponRepository;
import com.example.marketback.repository.boss.coupon.MemberCouponRepository;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.request.BossCouponRequest;
import com.example.marketback.request.BossMarketInfoRequest;
import com.example.marketback.request.MemberCouponRequest;
import com.example.marketback.response.BossBackProfileImg;
import com.example.marketback.response.BossCouponMemberResponse;
import com.example.marketback.response.BossCouponResponse;
import com.example.marketback.response.BossPriceMenuResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class BossServiceImpl implements BossService{

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BossRepository bossRepository;

    @Autowired
    private BossImgRepository bossImgRepository;

    @Autowired
    private BossPriceRepository bossPriceRepository;

    @Autowired
    private BossCouponRepository bossCouponRepository;

    @Autowired
    private MemberCouponRepository memberCouponRepository;

    @Override
    public boolean checkBossMember(String id) {
        Member isBossMember = memberRepository.findByMemberId(id);

        System.out.println(isBossMember.getBossAuth());

        return isBossMember.getBossAuth();
    }

    @Override
    public void register(String fileSrc, String id, String name, String region, String category) {
        Member memberEntity = memberRepository.findByMemberId(id);
        memberEntity.setBossAuth(Boolean.TRUE);
        Boss boss = new Boss(name, region, fileSrc, "address", category, memberEntity);

        bossRepository.save(boss);
    }

    @Override
    public Boss getPage(Long memberNo) {
        return bossRepository.findByMemberNo(memberNo);
    }

    @Override
    public void saveMarketInfo(BossMarketInfoRequest bossMarketInfoRequest) {
        Boss bossEntity = bossRepository.findByMemberId(bossMarketInfoRequest.getId());

        bossEntity.setPhoneNumber(bossMarketInfoRequest.getPhoneNumber());
        bossEntity.setMarketInfo(bossMarketInfoRequest.getMarketInfo());
        bossEntity.setStartTime(bossMarketInfoRequest.getStartTime());
        bossEntity.setEndTime(bossMarketInfoRequest.getEndTime());
        bossEntity.setAddress(bossMarketInfoRequest.getAddress());
        bossEntity.setMarketHomePage(bossMarketInfoRequest.getMarketHomePage());
        bossEntity.setLat(bossMarketInfoRequest.getLat());
        bossEntity.setLng(bossMarketInfoRequest.getLng());

        bossRepository.save(bossEntity);
    }

    @Override
    public void saveBackImg(String id, String name, List<String> fileName) {
        Boss boss = bossRepository.findByMemberId(id);

        for (String file : fileName){
            BossImage bossImage = new BossImage(file, boss);
            bossImgRepository.save(bossImage);
        }
    }

    @Override
    public void modifyProfile(String fileSrc, String id) {
        Boss bossEntity = bossRepository.findByMemberId(id);
        bossEntity.setProfileImg(fileSrc);

        bossRepository.save(bossEntity);
    }

    @Override
    public List<BossBackProfileImg> getBackProfile(Long bossNo) {
        log.info("id: "+bossNo);
        List<BossImage> bossImages = bossImgRepository.findImgListByBossNo(bossNo);
        List<BossBackProfileImg> response = new ArrayList<>();

        if(bossImages.size() != 0) {
            for (BossImage bossImage : bossImages) {
                response.add(new BossBackProfileImg(bossImage.getImageName()));
            }
            return response;
        }else {
            return null;
        }
    }

    @Override
    public void addPrice(BossPrice bossPrice, Long bossNo) {
        Boss bossEntity = bossRepository.findByBossNo(bossNo);

        bossPrice.setBoss(bossEntity);
        bossPriceRepository.save(bossPrice);
    }

    @Override
    public List<BossPriceMenuResponse> getMenu(String id) {
        List<BossPrice> bossPriceEntity = bossPriceRepository.findByMemberId(id);

        List<BossPriceMenuResponse> responses = new ArrayList<>();

        for(BossPrice bossPrice : bossPriceEntity){
            responses.add(new BossPriceMenuResponse(bossPrice.getBossPriceNo(), bossPrice.getMenuName(), bossPrice.getMenuPrice(), bossPrice.getMenuInfo()));
        }

        return responses;
    }

    @Override
    public void modifyMenu(BossPrice bossPrice) {
        BossPrice bossPriceEntity = bossPriceRepository.findBossByBossId(bossPrice.getBossPriceNo());
        bossPrice.setBoss(bossPriceEntity.getBoss());

        bossPriceRepository.save(bossPrice);
    }

    @Override
    public void deleteMenu(Long menuNo) {
        bossPriceRepository.deleteById(menuNo);
    }

    @Override
    public void addCoupon(BossCouponRequest bossCouponRequest) {
        Boss bossEntity = bossRepository.findByBossNo(bossCouponRequest.getBossNo());
        System.out.println(bossCouponRequest.getCouponMax()+"max!!!!!!!!!!!");

        Coupon coupon = new Coupon(bossCouponRequest.getCouponName(), bossCouponRequest.getCouponDate(), bossCouponRequest.getCouponMax(), bossCouponRequest.getCouponInfo(), 0, bossEntity);

        bossCouponRepository.save(coupon);
    }

    @Override
    public List<BossCouponResponse> showCoupon(Long bossNo) {
        List<Coupon> bossCoupon = bossCouponRepository.findByBossNo(bossNo);
        List<BossCouponResponse> responses = new ArrayList<>();

        if(bossCoupon.size() > 0) {
            for (Coupon coupon : bossCoupon) {
                responses.add(new BossCouponResponse(coupon.getCouponNo(), coupon.getCouponName(), coupon.getCouponInfo(), coupon.getCouponDate(), coupon.getCouponMax(), coupon.getGiveCoupon()));
            }
        }else {
            return null;
        }
        return responses;
    }

    @Override
    public void modifyCoupon(Coupon coupon) {
        Coupon couponEntity = bossCouponRepository.findByCouponNo(coupon.getCouponNo());

        coupon.setBoss(couponEntity.getBoss());
        bossCouponRepository.save(coupon);
    }

    @Override
    public ResponseEntity<Boolean> getCoupon(MemberCouponRequest memberCouponRequest) {
        Coupon coupon = bossCouponRepository.findByCouponNo(memberCouponRequest.getCouponNo());
        Member member = memberRepository.findByMemberId(memberCouponRequest.getId());
        Optional<MemberCoupon> ifGetMember = memberCouponRepository.findByMemberIdAndCouponNo(memberCouponRequest.getId(), memberCouponRequest.getCouponNo());

        if(coupon.getCouponMax() == null || coupon.getCouponMax() >= coupon.getGiveCoupon() ) {
            if (ifGetMember.isEmpty()) {
                coupon.setGiveCoupon(coupon.getGiveCoupon() + 1);
                MemberCoupon memberCoupon = new MemberCoupon(coupon, member);

                bossCouponRepository.save(coupon);
                memberCouponRepository.save(memberCoupon);

                return ResponseEntity.ok(Boolean.TRUE);
            }else {
                return ResponseEntity.ok(Boolean.FALSE);
            }
        }

        return ResponseEntity.ok(Boolean.FALSE);
    }

    @Override
    public List<BossCouponMemberResponse> couponMember(Long bossNo, Integer page) {
        Pageable pageable = PageRequest.of(page,6, Sort.Direction.DESC, "createDate");
        List<MemberCoupon> memberCoupon = memberCouponRepository.findByBossNo(bossNo, pageable);

        List<BossCouponMemberResponse> memberName = new ArrayList<>();

        for(MemberCoupon member : memberCoupon){
            memberName.add(new BossCouponMemberResponse(member.getMember().getName(), member.getMember().getProfileImg()));
        }

        return memberName;
    }

    @Override
    public Integer getTotalPage(Long bossNo) {
        Pageable pageable = PageRequest.of(0,6, Sort.Direction.DESC, "createDate");
        Page<MemberCoupon> reportList = memberCouponRepository.findPageByBossNo(bossNo, pageable);

        return reportList.getTotalPages();
    }

    @Override
    public void deleteCoupon(Long couponNo) {
        List<MemberCoupon> memberCouponList = memberCouponRepository.findByCouponNo(couponNo);
        Coupon coupon = bossCouponRepository.findByCouponNo(couponNo);

        memberCouponRepository.deleteAll(memberCouponList);
        bossCouponRepository.delete(coupon);
    }

}
