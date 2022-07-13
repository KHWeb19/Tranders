package com.example.marketback.service.follow;

import com.example.marketback.entity.follow.Follow;
import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.repository.follow.FollowRepository;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.repository.productBoard.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    FollowRepository followRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public boolean register(Long loginNo, Long memberNo, Follow follow) {
        Member my = memberRepository.findById(loginNo).orElseThrow();
        Member your = memberRepository.findById(memberNo).orElseThrow();

        if(followRepository.findByMyAndYour(my, your).isEmpty()) {
            follow.setMy(my);
            follow.setYour(your);
            followRepository.save(follow);
            return true;
        } else {
            Optional<Follow> maybeFollow = followRepository.findByMyAndYour(my, your);
            followRepository.deleteById(maybeFollow.get().getFollowNo());
            return false;
        }
    }

    @Override
    public boolean onFollow(Long loginNo, Long memberNo) {
        Member my = memberRepository.findById(loginNo).orElseThrow();
        Member your = memberRepository.findById(memberNo).orElseThrow();

        if(followRepository.findByMyAndYour(my, your).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Follow> followList(Long memberNo) {
        return followRepository.findByLoginNo(Long.valueOf(memberNo));
    }

    @Override
    public List<ProductBoard> followBoardList(Long memberNo) {
        List<Follow> followList = followRepository.findByLoginNo(Long.valueOf(memberNo));
        List<ProductBoard> followBoard = new ArrayList<>();

        for (Follow f : followList) {
            List<ProductBoard> boards = productRepository.findByMemberNoInProductBoard(Long.valueOf(f.getYour().getMemberNo()));
            for (ProductBoard b : boards) {
                followBoard.add(b);
            }
        }
        return followBoard;
    }
}

