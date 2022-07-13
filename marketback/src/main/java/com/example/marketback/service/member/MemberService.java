package com.example.marketback.service.member;

import com.example.marketback.entity.member.City;
import com.example.marketback.entity.member.Member;
import com.example.marketback.request.MemberCouponRequest;
import com.example.marketback.request.MemberLoginRequest;
import com.example.marketback.request.MemberModifySnsRequest;
import com.example.marketback.response.CityVillageInfoResponse;
import com.example.marketback.response.MemberRegionResponse;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public interface MemberService {
    void register(Member member);
    Boolean checkIdDuplicate(String id);
    Member getMember(String id);
    Member registerMember(String email);
    String getMemberProfile(String id);

    Boolean myPageCheck(MemberLoginRequest memberLoginRequest);

    void modify(Member member);

    void modifyPw(MemberLoginRequest memberLoginRequest);

    void modifySns(MemberModifySnsRequest memberModifySnsRequest);

    void modifyProfileImg(String fileSrc, String id);

    MemberRegionResponse getRegion(String id);

    void modifyRegion(MemberRegionResponse memberRegionResponse, String id);

    void snsRegister(Member member);

    CityVillageInfoResponse getVillageInfo(String id);

    Integer saveVillageSetting(String id, String data);

    void couponUse(MemberCouponRequest memberCouponRequest);


    String phoneNumCheck(String phoneNumber) throws CoolsmsException;
}
