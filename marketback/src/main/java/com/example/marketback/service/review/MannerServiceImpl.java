package com.example.marketback.service.review;

import com.example.marketback.entity.chatting.ChatRoom;
import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.review.Manner;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.repository.review.MannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MannerServiceImpl implements MannerService {

    @Autowired
    private MannerRepository mannerRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void register(Manner manner, String memberId) {
        Member maybeMember = memberRepository.findByMemberId(memberId);
        Optional<Manner> maybeManner = mannerRepository.findByMemberId(memberId);

        if (maybeManner.equals(Optional.empty())) {
            manner.setMember(maybeMember);
            mannerRepository.save(manner);
        } else {
            maybeManner.get().setGood1(maybeManner.get().getGood1()+manner.getGood1());
            maybeManner.get().setGood2(maybeManner.get().getGood2()+manner.getGood2());
            maybeManner.get().setGood3(maybeManner.get().getGood3()+manner.getGood3());
            maybeManner.get().setGood4(maybeManner.get().getGood4()+manner.getGood4());
            maybeManner.get().setBad1(maybeManner.get().getBad1()+manner.getBad1());
            maybeManner.get().setBad2(maybeManner.get().getBad2()+manner.getBad2());
            maybeManner.get().setBad3(maybeManner.get().getBad3()+manner.getBad3());
            maybeManner.get().setBad4(maybeManner.get().getBad4()+manner.getBad4());
            mannerRepository.save(maybeManner.get());
        }
    }
    public Manner read(String memberId) {
        Optional<Manner> maybeManner = mannerRepository.findByMemberId(memberId);

        if (maybeManner.equals(Optional.empty())) {
            return null;
        }

        return maybeManner.get();
    }
}
