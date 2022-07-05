package com.example.marketback.entity.jpa.community;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.near.Near;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class CommunityBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    @Column(length = 128, nullable = false)
    private String usedSubject;

    @Column(length = 128, nullable = false)
    private String title;

    @Column(length = 32, nullable = false)
    private String writer;

    @Column(length = 32, nullable = false)
    private String region;

    @Column(length = 128)
    private String placeName;

    @Column(length = 128)
    private String placeUrl;

    @Lob
    private String content;

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;

    @Formula("(SELECT count(1) FROM community_board_like c WHERE c.community_board_board_no = board_no)")
    private Integer likeCnt;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "communityBoard", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CommunityBoardLike> likes = new HashSet<>();

    @Column
    private String fileName1;

    @Column
    private String fileName2;

    @Column
    private String fileName3;

    @Column
    private String fileName4;

    @Column
    private String fileName5;

    @Column
    private String fileName6;

    @Column
    private String fileName7;

    @Column
    private String fileName8;

    @Column
    private String fileName9;

    @Column
    private String fileName10;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NEAR_NO")
    private Near near;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "BOSS_AUTH_NO")
    private Boss boss;

    @Formula("(SELECT count(1) FROM community_comment c WHERE c.community_board_board_no = board_no)")
    private int commentCnt;

    @PrePersist
    public void onPrePersist() {
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    }

    @PreUpdate
    public void onPreUpdate() {
        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    }

    public CommunityBoard(String fileName1, String fileName2){
        this.fileName1 = fileName1;
        this.fileName2 = fileName2;
    }

    @OneToMany(mappedBy = "communityBoard", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<CommunityComment> comments;
}