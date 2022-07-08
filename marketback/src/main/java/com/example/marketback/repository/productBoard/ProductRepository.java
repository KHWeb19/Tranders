package com.example.marketback.repository.productBoard;

import com.example.marketback.entity.productBoard.ProductBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductBoard, Long> {

    @Query("select p from ProductBoard p where p.member.region = :region and p.content like %:keyWord%")
    List<ProductBoard> findAllByContentContain(String keyWord, String region, Pageable pageable);

    @Query("select p from ProductBoard p where p.productNo = :productNo")
    ProductBoard findByProductNo(Long productNo);

    @Query("select p from ProductBoard p where p.member.city.villageName = :region and p.content like %:keyWord%")
    List<ProductBoard> findAllByVillageNameContentContain(String keyWord, String region, Pageable pageable);

    @Query("select p from ProductBoard p where p.member.city.district = :region and p.content like %:keyWord% and not p.member.id = :id")
    List<ProductBoard> findAllByDistrictContentContain(String keyWord, String region, Pageable pageable);

    @Query("select p from ProductBoard p where p.member.city.city = :region and p.content like %:keyWord%")
    List<ProductBoard> findAllByCityContentContain(String keyWord, String region, Pageable pageable);
}
