package com.example.marketback.entity.near;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Near { // 이거 애매하다..

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NEAR_NO")
    private Long nearNo;

    private String placeName;

    private String category;

    private String phoneNumber;

    private String marketHomePage;

    private String address;

    private String storeRegion;

    private String lat;

    private String lng;

    private Integer reviewCount;

    private Integer communityCount;

    @OneToMany(mappedBy = "near")
    private List<CommunityBoard> communityBoardsList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "MEMBER_NO") // boss가 되기 위해서
    private Member member;

    public void newComm(CommunityBoard communityBoard){
        this.communityBoardsList.add(communityBoard);
    }
}
