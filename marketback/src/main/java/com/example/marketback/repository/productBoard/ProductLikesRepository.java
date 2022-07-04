package com.example.marketback.repository.productBoard;

import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.entity.productBoard.ProductLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductLikesRepository extends JpaRepository<ProductLikes, Long> {

    Optional<ProductLikes> findByMemberAndProductBoard (Member member, ProductBoard productBoard);

    @Query(value = "select * from productLikes where product_no in(select product_no from product_board where product_no = :productNo) and MEMBER_NO(select MEMBER_NO from member where MEMBER_NO = :memberNo)", nativeQuery = true)
    Optional<ProductLikes> findLikesProductBoardAndMemberNo(@Param("productNo") Long productNo, @Param("memberNo") Long memberNo);

    @Query(value = "select * from productLikes where product_no in(select product_no from product_board where product_no = :productNo)", nativeQuery = true)
    List<ProductLikes> findByProductNo(@Param("productNo") Long productNo);

    @Query(value = "select * from productLikes where member_no in(select member_no from member where member_no = :memberNo) order by product_no desc", nativeQuery = true)
    List<ProductLikes> findByMemberNo(@Param("memberNo") Long memberNo);

    @Query("select l from ProductLikes l join l.productBoard lp where lp.productNo = :productNo")
    List<ProductLikes> findAllLikesProductNo(@Param("productNo") Long productNo);

    @Query("select l from ProductLikes l join l.member lm where lm.memberNo = :memberNo")
    List<ProductLikes> findAllLikesMemberNo(@Param("memberNo") Long memberNo);

}
