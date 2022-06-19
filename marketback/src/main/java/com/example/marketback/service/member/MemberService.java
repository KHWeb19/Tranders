package com.example.marketback.service.member;

import com.example.marketback.entity.member.Member;
import com.example.marketback.request.MemberLoginRequest;

public interface MemberService {
    void register(Member member);
    Boolean checkIdDuplicate(String id);
    Member getMember(String id);
    Member registerMember(String email);
    String getMemberProfile(String id);

    Boolean myPageCheck(MemberLoginRequest memberLoginRequest);

    void modify(Member member);

    void modifyProfileImg(String fileSrc, String id);
}
