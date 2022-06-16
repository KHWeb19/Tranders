package com.example.marketback.entity.member;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String address;

    private Float temperature;

    private Boolean bossAuth;

    @Enumerated(EnumType.STRING)
    private MemberRole roles;

    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @CreationTimestamp
    private Timestamp createDate;

    public final Member memberSetting(Member member){
        member.setTemperature(36.5F);
        member.setBossAuth(Boolean.FALSE);

        return member;
    }
}
