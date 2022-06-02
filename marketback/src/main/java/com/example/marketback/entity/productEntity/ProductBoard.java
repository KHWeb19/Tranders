package com.example.marketback.entity.productEntity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class ProductBoard {


    //private Integer price;

    //private String category;
    //private List image;
    //private Long memberNo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productNo;

    @Column(length = 128, nullable = false)
    private String title;

    @Lob
    private String content;

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date updDate;
}
