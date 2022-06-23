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
public class BossPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bossPriceNo;

    private String menuName;

    private String menuPrice;

    @Lob
    private String menuInfo;

    @ManyToOne
    @JoinColumn(name = "BOSS_AUTH_NO")
    private Boss boss;

}
