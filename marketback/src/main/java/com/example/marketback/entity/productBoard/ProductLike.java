package com.example.marketback.entity.productBoard;

import com.example.marketback.entity.member.Member;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_like")
public class ProductLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="like_no")
    private Long likeNo;

    @JsonIgnoreProperties({"productLike", "productBoards"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "member_no")
    private Member member;

    @JsonIgnoreProperties({"member"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "product_no")
    private ProductBoard productBoard;
}
