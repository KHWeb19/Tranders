package com.example.marketback.entity.productBoard;

import com.example.marketback.entity.member.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product_board")
public class ProductBoard {

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

    @Column(length = 64, nullable = false)
    private String productImage;

    @Column(length = 64)
    private String productImage1;

    @Column(length = 64)
    private String productImage2;

    @Column(length = 64, nullable = false)
    private String category;

    @Column(nullable = false)
    private Integer viewCnt = 0;

    public void increaseViewCnt() {
        this.viewCnt++;
    }

    @Column(nullable = false)
    private Integer chatCnt = 0;

    @Column(nullable = false)
    private String process;

    @CreationTimestamp
    private Date regDate;

    @JsonIgnoreProperties({"productBoards"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_no")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Member buyer;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnoreProperties({"productBoards", "productBoard"})
    @OneToMany(mappedBy = "productBoard", fetch = FetchType.EAGER)
    private Set<ProductLike> productLike = new HashSet<>();
}
