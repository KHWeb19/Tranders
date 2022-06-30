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
            Manner mannerEntity = new Manner(
                    maybeMember,
                    manner.getCount1(),
                    manner.getCount2(),
                    manner.getCount3(),
                    manner.getCount4()
            );
            mannerRepository.save(mannerEntity);
        } else {
            maybeManner.get().setCount1(maybeManner.get().getCount1()+manner.getCount1());
            maybeManner.get().setCount2(maybeManner.get().getCount2()+manner.getCount2());
            maybeManner.get().setCount3(maybeManner.get().getCount3()+manner.getCount3());
            maybeManner.get().setCount4(maybeManner.get().getCount4()+manner.getCount4());
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
