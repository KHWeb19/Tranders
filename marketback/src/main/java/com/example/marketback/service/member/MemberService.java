package com.example.marketback.service.member;

import com.example.marketback.entity.Member;

public interface MemberService {
    void register(Member member);
    Boolean checkIdDuplicate(String id);
    Member getMember(String id);
    Member registerMember(String email);
}
