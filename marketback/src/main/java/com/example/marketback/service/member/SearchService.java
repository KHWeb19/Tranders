package com.example.marketback.service.member;

import com.example.marketback.request.MemberLoginRequest;

public interface SearchService {
    String searchId(String name, String phoneNumber);

    String searchPw(String id, String name, String phoneNumber);

    void changePw(MemberLoginRequest memberLoginRequest);
}
