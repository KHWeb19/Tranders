package com.example.marketback.repository.member;

import com.example.marketback.entity.member.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("select c from City c where c.villageName = :region")
    City findByRegion(String region);
}
