package com.example.marketback.service.member;

import com.example.marketback.entity.Member;
import com.example.marketback.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
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

    @Override
    public Member getMember(String id) {
        return memberRepository.findByMemberName(id);
    }

    @Override
    public Member registerMember(String email) {
        return memberRepository.findByEmail(email);
    }
}
