package com.example.marketback.service.member;

import com.example.marketback.entity.member.Member;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.request.MemberLoginRequest;
import com.example.marketback.response.MemberRegionResponse;
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

        if(member.getPassword() != null){
            String encodePassword = passwordEncoder.encode(member.getPassword());
            memberEntity.setPassword(encodePassword);
        }

        memberEntity.setName(member.getName());
        memberEntity.setPhoneNumber(member.getPhoneNumber());

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

}
