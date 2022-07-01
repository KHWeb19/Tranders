package com.example.marketback.entity.review;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.near.Near;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BossReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOSS_REVIEW_NO")
    private Long bossReviewNo;

    private String content;

    private String state;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "BOSS_AUTH_NO")
    private Boss boss;

    @ManyToOne
    @JoinColumn(name = "boardNo")
    private CommunityBoard communityBoard;

    @ManyToOne
    @JoinColumn(name = "NEAR_NO")
    private Near near;

    @OneToMany(mappedBy = "review")
    private List<BossReviewImage> bossReviewImagesList = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdDate;

    public BossReview(String content, String state, Member member, Boss boss, Near near) {
        this.content = content;
        this.state = state;
        this.member = member;
        this.boss = boss;
        this.near = near;
    }
}
