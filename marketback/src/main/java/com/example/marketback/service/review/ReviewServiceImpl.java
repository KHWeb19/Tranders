package com.example.marketback.service.review;

import com.example.marketback.entity.review.Review;
import com.example.marketback.entity.member.Member;
import com.example.marketback.repository.review.ReviewRepository;
import com.example.marketback.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void register(Review review, String memberId, String member2Id) {
        Member memberEntity = memberRepository.findByMemberId(memberId);
        Member member2Entity = memberRepository.findByMemberId(member2Id);
        memberEntity.setTemperature(memberEntity.getTemperature()+review.getSum());
        review.setMember(memberEntity);
        review.setWriter(member2Entity);
        reviewRepository.save(review);
        memberRepository.save(memberEntity);
    }

    @Override
    public List<Review> list(String id) {
        return reviewRepository.findReviewById(id);
    }

}
