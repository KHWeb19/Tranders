package com.example.marketback.repository.productBoard;

import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.entity.productBoard.ProductLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Long> {
    Optional<ProductLike> findByMemberAndProductBoard (Member member, ProductBoard productBoard);

    @Query(value = "select * from product_like where product_no in(select product_no from product_board where product_no = :productNo) and MEMBER_NO in(select MEMBER_NO from member where MEMBER_NO = :memberNo)", nativeQuery = true)
    Optional<ProductLike> findLikesProductNoAndMemberNo(@Param("productNo") Long productNo, @Param("memberNo") Long memberNo);

    @Query(value = "select * from product_like where product_no in(select product_no from product_board where product_no = :productNo)", nativeQuery = true)
    List<ProductLike> findByProductNo(@Param("productNo") Long productNo);

    @Query(value = "select * from product_like where MEMBER_NO in(select MEMBER_NO from member where MEMBER_NO = :memberNo) order by product_no desc", nativeQuery = true)
    List<ProductLike> findByMemberNo(@Param("memberNo") Long memberNo);

    @Query("select l from ProductLike l join l.productBoard cb where cb.productNo = :productNo")
    List<ProductLike> findAllLikesProductNo(@Param("productNo") Long productNo);

    @Query("select l from ProductLike l join l.member cd where cd.memberNo = :memberNo")
    List<ProductLike> findAllLikesMemberNo(@Param("memberNo") Long memberNo);
}
