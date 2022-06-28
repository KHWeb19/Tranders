package com.example.marketback.entity.review;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOSS_REVIEW_NO")
    private Long boseReviewNo;

    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "BOSS_AUTH_NO")
    private Boss boss;

}
