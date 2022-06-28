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

    @Lob
    private String marketInfo;

    private String phoneNumber;

    private String marketHomePage;

    private String startTime;

    private String endTime;

    private String lat;

    private String lng;

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

    public Boss(String placeName, String lat, String lng) {
        this.placeName = placeName;
        this.lat = lat;
        this.lng = lng;
    }
}
