package com.example.marketback.service.member;

import com.example.marketback.entity.boss.coupon.CouponState;
import com.example.marketback.entity.boss.coupon.MemberCoupon;
import com.example.marketback.entity.member.City;
import com.example.marketback.entity.member.Member;
import com.example.marketback.repository.boss.coupon.BossCouponRepository;
import com.example.marketback.repository.boss.coupon.MemberCouponRepository;
import com.example.marketback.repository.member.CityRepository;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.request.MemberCouponRequest;
import com.example.marketback.request.MemberLoginRequest;
import com.example.marketback.request.MemberModifySnsRequest;
import com.example.marketback.response.CityVillageInfoResponse;
import com.example.marketback.response.MemberRegionResponse;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private BossCouponRepository bossCouponRepository;

    @Autowired
    private MemberCouponRepository memberCouponRepository;

    @Override
    public void register(Member member) {
        String encodePassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodePassword);

        Member memberEntity = member.memberSetting(member);
        City city = cityRepository.findByRegion(member.getRegion());
        memberEntity.setCity(city);

        memberRepository.save(memberEntity);
    }

    @Override
    public Boolean checkIdDuplicate(String id) {
        Optional<Member> check = memberRepository.checkDubById(id);
        System.out.println("check : " + check.isEmpty());
        return check.isEmpty();
    }

    @Override
    public Member getMember(String id) {
        return memberRepository.findByMemberId(id);
    }

    @Override
    public Member registerMember(String email) {
        return memberRepository.findByMemberId(email);
    }

    @Override
    public String getMemberProfile(String id) {
        Member member = memberRepository.findByMemberId(id);
        return member.getProfileImg();
    }

    @Override
    public Boolean myPageCheck(MemberLoginRequest memberLoginRequest) {
        Member member = memberRepository.findByMemberId(memberLoginRequest.getId());

        if(!passwordEncoder.matches(memberLoginRequest.getPassword(), member.getPassword())){
            log.info("Wrong password!");
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void modify(Member member) {
        Member memberEntity = memberRepository.findByMemberId(member.getId());

        memberEntity.setName(member.getName());
        memberEntity.setPhoneNumber(member.getPhoneNumber());

        memberRepository.save(memberEntity);
    }

    @Override
    public void modifyPw(MemberLoginRequest memberLoginRequest) {
        Member memberEntity = memberRepository.findByMemberId(memberLoginRequest.getId());
        String encodePassword = passwordEncoder.encode(memberLoginRequest.getPassword());
        memberEntity.setPassword(encodePassword);

        memberRepository.save(memberEntity);
    }

    @Override
    public void modifySns(MemberModifySnsRequest memberModifySnsRequest) {
        Member memberEntity = memberRepository.findByMemberId(memberModifySnsRequest.getId());
        memberEntity.setName(memberModifySnsRequest.getName());

        memberRepository.save(memberEntity);
    }

    @Override
    public void modifyProfileImg(String fileSrc, String id) {
        Member memberEntity = memberRepository.findByMemberId(id);

        memberEntity.setProfileImg(fileSrc);
        memberRepository.save(memberEntity);
    }

    @Override
    public MemberRegionResponse getRegion(String id) {
        Member memberEntity = memberRepository.findByMemberId(id);

        log.info(memberEntity.getLat()+", "+ memberEntity.getLng()+"!!!!");
        return new MemberRegionResponse(memberEntity.getLat(), memberEntity.getLng(), memberEntity.getRegion());
    }

    @Override
    public void modifyRegion(MemberRegionResponse memberRegionResponse, String id) {
        Member memberEntity = memberRepository.findByMemberId(id);

        memberEntity.setRegion(memberRegionResponse.getRegion());
        memberEntity.setLat(memberRegionResponse.getLat());
        memberEntity.setLng(memberRegionResponse.getLng());

        memberRepository.save(memberEntity);
    }

    @Override
    public void snsRegister(Member member) {
        Member memberEntity = memberRepository.findByMemberId(member.getId());

        memberEntity.setName(member.getName());
        memberEntity.setRegion(member.getRegion());
        memberEntity.setLat(member.getLat());
        memberEntity.setLng(member.getLng());
        memberEntity.setRegisterStatus(Boolean.TRUE);

        memberRepository.save(memberEntity);
    }

    @Override
    public CityVillageInfoResponse getVillageInfo(String id) {
        Member memberEntity = memberRepository.findByMemberId(id);
        int searchNum;

        if(memberEntity.getSearchRegion().charAt(memberEntity.getSearchRegion().length() -1) == '동'){
            searchNum = 2;
        }else if(memberEntity.getSearchRegion().charAt(memberEntity.getSearchRegion().length() -1) == '구'){
            searchNum = 1;
        }else {
            searchNum = 0;
        }

        return new CityVillageInfoResponse(memberEntity.getCity().getCity(), memberEntity.getCity().getDistrict(), memberEntity.getCity().getVillageName(), searchNum);
    }

    @Override
    public Integer saveVillageSetting(String id, String data) {
        Member memberEntity = memberRepository.findByMemberId(id);

        memberEntity.setSearchRegion(data);
        memberRepository.save(memberEntity);

        System.out.println(data.charAt(data.length() -1));
        if(data.charAt(data.length() -1) == '동'){
            return 2;
        }else if(data.charAt(data.length() -1) == '구'){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public void couponUse(MemberCouponRequest memberCouponRequest) {
        MemberCoupon memberCoupon = memberCouponRepository.findOneByMemberIdAndCouponNo(memberCouponRequest.getId(), memberCouponRequest.getCouponNo());

        memberCoupon.getCoupon().setCouponState(CouponState.USE);
        memberCouponRepository.save(memberCoupon);
    }

    /*@Override
    public String phoneNumCheck(String phoneNumber) throws CoolsmsException {
        Optional<Member> memberEntity = memberRepository.findByPhoneNumb(phoneNumber);

        if (memberEntity.isEmpty()) {

            String api_key = "NCSLU6CNDMVJURJO";
            String api_secret = "SONBUTACVQLQ7URQBDS1XN2A6PISGFYE";

            Message coolsms = new Message(api_key, api_secret);

            Random rand = new Random();
            String numStr = "";
            for (int i = 0; i < 4; i++) {
                String ran = Integer.toString(rand.nextInt(10));
                numStr += ran;
            }

            HashMap<String, String> params = new HashMap<String, String>();
            params.put("to", phoneNumber);
            params.put("from", "전화번호");
            params.put("type", "sms");
            params.put("text", "인증번호는 [" + numStr + "] 입니다.");

            coolsms.send(params); // 메시지 전송

            return numStr;
        } else {

            return "error";
        }
    }*/

}
