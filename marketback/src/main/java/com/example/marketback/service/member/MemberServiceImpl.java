package com.example.marketback.service.member;

import com.example.marketback.entity.Member;
import com.example.marketback.entity.MemberRole;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.request.MemberRegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void register(Member member) {
        String encodePassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodePassword);

        Member memberEntity = member.memberSetting(member);

        memberRepository.save(memberEntity);
    }

    @Override
    public Boolean checkIdDuplicate(String id) {
        Optional<Member> check = memberRepository.checkDubById(id);
        System.out.println("check : " + check.isEmpty());
        return check.isEmpty();
    }
}
