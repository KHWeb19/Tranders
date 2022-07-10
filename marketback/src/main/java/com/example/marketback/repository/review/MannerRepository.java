package com.example.marketback.repository.review;

import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.review.Manner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MannerRepository extends JpaRepository<Manner, Long> {
    @Query("select m from Manner m join m.member mm where mm.id = :memberId")
    Optional<Manner> findByMemberId(String memberId);

    @Query("select m from Manner m where m.member.id = :id")
    List<Manner> findRemoveByMemberId(String id);
}
