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
public class BossImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bossImageNo;

    private String imageName;

    @ManyToOne
    @JoinColumn(name = "BOSS_AUTH_NO")
    private Boss boss;

    public BossImage(String imageName, Boss boss) {
        this.imageName = imageName;
        this.boss = boss;
    }
}
