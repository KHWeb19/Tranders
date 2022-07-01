package com.example.marketback.entity.review;

import com.example.marketback.entity.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@NoArgsConstructor
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_no")
    private Long reviewNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Member writer;

    @Column
    private Float sum;

    @Column(length = 64)
    private String message;

    @CreatedDate
    private String regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
}
