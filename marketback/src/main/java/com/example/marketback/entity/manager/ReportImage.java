package com.example.marketback.entity.manager;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportImpNo;

    private String imageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORT_NO")
    private Report report;

    public ReportImage(String imageName, Report report) {
        this.imageName = imageName;
        this.report = report;
    }
}
