package com.example.marketback.entity.manager;

import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.productBoard.ProductBoard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPORT_NO")
    private Long reportNo;

    private String reportCategory1;

    private String reportCategory2;

    @Lob
    private String content;

    private Boolean banCheck;

    @ManyToOne
    @JoinColumn
    private Member toMember;  // to

    @ManyToOne
    @JoinColumn
    private Member fromMember;  // to

    @ManyToOne
    @JoinColumn(name = "boardNo")
    private ProductBoard productBoard;

    @Enumerated(EnumType.STRING)
    private ReportState reportState;

    @CreatedDate
    private String createDate = String.valueOf(LocalDateTime.now());

    public Report(String reportCategory1, String reportCategory2, String content, Boolean banCheck, Member toMember, Member fromMember, ProductBoard productBoard) {
        this.reportCategory1 = reportCategory1;
        this.reportCategory2 = reportCategory2;
        this.content = content;
        this.banCheck = banCheck;
        this.toMember = toMember;
        this.fromMember = fromMember;
        this.productBoard = productBoard;
    }
}
