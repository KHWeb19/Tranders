package com.example.marketback.entity.follow;

import com.example.marketback.entity.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="follow_no")
    private Long followNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Member my;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Member your;
}
