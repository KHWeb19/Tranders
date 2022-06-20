package com.example.marketback.service.member;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.member.Member;
import com.example.marketback.repository.boss.BossRepository;
import com.example.marketback.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class BossServiceImpl implements BossService{

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BossRepository bossRepository;

    @Override
    public boolean checkBossMember(String id) {
        Member isBossMember = memberRepository.findByMemberId(id);

        System.out.println(isBossMember.getBossAuth());

        return isBossMember.getBossAuth();
    }

    @Override
    public void register(String fileSrc, String id, String name, String region, String category) {
        Member memberEntity = memberRepository.findByMemberId(id);
        memberEntity.setBossAuth(Boolean.TRUE);
        Boss boss = new Boss(name, region, fileSrc, "address", category, memberEntity);

        bossRepository.save(boss);
    }

    @Override
    public Boss getPage(Long memberNo) {
        return bossRepository.findByMemberNo(memberNo);
    }
}
