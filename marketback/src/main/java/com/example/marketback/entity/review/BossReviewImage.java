package com.example.marketback.entity.review;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BossReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewImageNo;

    private String imageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOSS_REVIEW_NO")
    private BossReview review;

    public BossReviewImage(String imageName, BossReview review) {
        this.imageName = imageName;
        this.review = review;
    }
}
