package com.example.marketback.entity.productBoard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product_board")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_no")
    private Long productNo;

    @Column(length = 64, nullable = false)
    private String title;

    @Column(length = 64, nullable = false)
    private Integer price;

    @Lob
    private String content;

    @CreatedDate
    private String regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));

    @UpdateTimestamp
    private Date updDate;

}
