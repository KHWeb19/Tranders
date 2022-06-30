package com.example.marketback.entity.review;

import com.example.marketback.entity.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "manner")
public class Manner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="manner_no")
    private Long mannerNo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Member member;

    @Column
    private Integer count1 = 0;

    @Column
    private Integer count2 = 0;

    @Column
    private Integer count3 = 0;

    @Column
    private Integer count4 = 0;

    public Manner(Member member, Integer count1, Integer count2, Integer count3, Integer count4){
        this.member = member;
        this.count1 = count1;
        this.count2 = count2;
        this.count3 = count3;
        this.count4 = count4;
    }
}
