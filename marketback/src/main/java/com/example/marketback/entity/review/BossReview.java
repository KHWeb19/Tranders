package com.example.marketback.entity.review;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BossReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOSS_REVIEW_NO")
    private Long boseReviewNo;

    private String content;

    private String state;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "BOSS_AUTH_NO")
    private Boss boss;

    @CreatedDate
    private LocalDateTime createdDate;

    public BossReview(String content, String state, Member member, Boss boss) {
        this.content = content;
        this.state = state;
        this.member = member;
        this.boss = boss;
    }
}
