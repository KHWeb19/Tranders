package com.example.marketback.entity.member;

import com.example.marketback.entity.boss.Boss;
import lombok.*;
import org.apache.kafka.common.protocol.types.Field;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_NO")
    private Long memberNo;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private String lng;

    @Column(nullable = false)
    private Boolean registerStatus;

    private Integer Money = 0;

    private String address;

    private Float temperature;

    private Boolean bossAuth;

    private String profileImg;

    private String searchRegion;

    @Enumerated(EnumType.STRING)
    private MemberRole roles;

    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @CreatedDate
    private String createDate = String.valueOf(LocalDateTime.now());

    public final Member memberSetting(Member member){
        member.setTemperature(36.5F);
        member.setBossAuth(Boolean.FALSE);
        member.setRegisterStatus(Boolean.TRUE);
        member.setSearchRegion(member.getRegion());

        return member;
    }
}
