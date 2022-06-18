package com.example.marketback.entity.boss;

import com.example.marketback.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "BossAuth")
public class Boss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOSS_AUTH_NO")
    private Long bossAuthNo;

    private String placeName;

    private String region;

    private String profileImg;

    private String address;

    private String category;

    @OneToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    public Boss(String placeName, String region, String profileImg, String address, String category, Member member) {
        this.placeName = placeName;
        this.region = region;
        this.profileImg = profileImg;
        this.address = address;
        this.category = category;
        this.member = member;
    }
}
