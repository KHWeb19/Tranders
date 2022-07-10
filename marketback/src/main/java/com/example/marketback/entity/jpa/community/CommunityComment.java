package com.example.marketback.entity.jpa.community;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class CommunityComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long commentId;

    @NotNull
    @Column
    private String comment;

    @NotNull
    @Column
    private String commentWriter;

    @NotNull
    @Column
    private String commentRegion;

    @Column(length = 128)
    private String placeName;

    @Column(length = 128)
    private String placeUrl;

    @Column
    private String fileName;


//    @CreatedDate
//    private String date = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    @CreatedDate
    private String regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));

    @UpdateTimestamp
    private Date updDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name ="communityBoard_boardNo")
    private CommunityBoard communityBoard;

    public CommunityComment(String comment, String commentWriter, String commentRegion, String fileName){
        this.comment = comment;
        this.commentWriter = commentWriter;
        this.commentRegion = commentRegion;
        this.fileName = fileName;
    }
}