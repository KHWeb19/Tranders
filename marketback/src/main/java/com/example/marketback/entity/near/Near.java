package com.example.marketback.entity.near;

import com.example.marketback.entity.boss.Boss;
import lombok.*;

import javax.persistence.*;

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

    private String lat;

    private String lng;

    @ManyToOne
    @JoinColumn(name = "BOSS_AUTH_NO")
    private Boss boss;

}
