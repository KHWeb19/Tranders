package com.example.marketback.repository.productBoard;

import com.example.marketback.entity.chatting.ChatRoom;
import com.example.marketback.entity.productBoard.ProductBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductBoard, Long> {

    List<ProductBoard> findByContentContaining(String keyWord);

    @Query("select p from ProductBoard p where p.productNo = :productNo")
    ProductBoard findByProductNo(Long productNo);

    @Query("select p from ProductBoard p join p.buyer pb where pb.memberNo = :memberNo")
    List<ProductBoard> findByBuyer(@Param("memberNo") Long memberNo);
}
