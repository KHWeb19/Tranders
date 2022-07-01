package com.example.marketback.repository.near;

import com.example.marketback.entity.near.Near;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NearRepository extends JpaRepository<Near, Long> {
    @Query("select n from Near n where n.address = :storeRegion")
    Optional<Near> findByAddress(String storeRegion);

    @Query("select n from Near n where n.nearNo = :nearNo")
    Near findByNearNo(Long nearNo);
}
