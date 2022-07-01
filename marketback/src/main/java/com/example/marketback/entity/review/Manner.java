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
    private Integer good1 = 0;

    @Column
    private Integer good2 = 0;

    @Column
    private Integer good3 = 0;

    @Column
    private Integer good4 = 0;

    @Column
    private Integer bad1 = 0;

    @Column
    private Integer bad2 = 0;

    @Column
    private Integer bad3 = 0;

    @Column
    private Integer bad4 = 0;

    public Manner(Member member, Integer good1, Integer good2, Integer good3, Integer good4, Integer bad1, Integer bad2, Integer bad3, Integer bad4){
        this.member = member;
        this.good1 = good1;
        this.good2 = good2;
        this.good3 = good3;
        this.good4 = good4;
        this.bad1 = bad1;
        this.bad2 = bad2;
        this.bad3 = bad3;
        this.bad4 = bad4;
    }
}
