package com.example.marketback.entity.manager;

import com.example.marketback.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class BanMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_NO")
    private Long banMemberNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member toMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member fromMember;

    public BanMember(Member toMember, Member fromMember) {
        this.toMember = toMember;
        this.fromMember = fromMember;
    }
}
