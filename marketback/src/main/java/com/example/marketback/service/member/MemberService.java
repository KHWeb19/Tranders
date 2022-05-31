package com.example.marketback.service.member;

import com.example.marketback.entity.Member;
import com.example.marketback.request.MemberRegisterRequest;

public interface MemberService {
    void register(Member member);

    Boolean checkIdDuplicate(String id);
}
