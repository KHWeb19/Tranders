package com.example.marketback.service.member;

import com.example.marketback.entity.member.Member;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.request.MemberLoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String searchId(String name, String phoneNumber) {
        Optional<Member> memberId = memberRepository.checkMember(name, phoneNumber);

        if(memberId.isEmpty()){
            log.info("해당 정보는 없어!");
            return null;
        }

        log.info(memberId.get().getId());

        return memberId.get().getId();
    }

    @Override
    public String searchPw(String id, String name, String phoneNumber) {

        Optional<Member> memberPw = memberRepository.findPwById(id, name, phoneNumber);

        log.info("비밀번호 변경가능한가요? : " +memberPw.isEmpty());

        if(memberPw.isEmpty()){
            return null;
        }

        return memberPw.get().getId();
    }

    @Override
    public void changePw(MemberLoginRequest memberLoginRequest) {
        Member member = memberRepository.findByMemberId(memberLoginRequest.getId());

        String encodePassword = passwordEncoder.encode(memberLoginRequest.getPassword());
        member.setPassword(encodePassword);

        memberRepository.save(member);
    }

}
