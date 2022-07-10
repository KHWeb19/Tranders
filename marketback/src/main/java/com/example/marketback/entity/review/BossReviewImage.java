package com.example.marketback.entity.review;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BossReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewImageNo;

    private String imageName;

    @CreatedDate
    private String createDate = String.valueOf(LocalDateTime.now());

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOSS_REVIEW_NO")
    private BossReview review;

    public BossReviewImage(String imageName, BossReview review) {
        this.imageName = imageName;
        this.review = review;
    }
}
