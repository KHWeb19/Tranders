package com.example.marketback.repository.productBoard;

import com.example.marketback.entity.productBoard.ProductBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductBoard, Long> {

    List<ProductBoard> findByContentContaining(String keyWord, Pageable pageable);

    @Query("select p from ProductBoard p where p.productNo = :productNo")
    ProductBoard findByProductNo(Long productNo);
}
