package com.example.marketback.repository.jpa.community;

import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.jpa.community.CommunityBoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommunityBoardLikeRepository extends JpaRepository<CommunityBoardLike, Long> {
    Optional<CommunityBoardLike> findByWhoAndCommunityBoard (String who, CommunityBoard communityBoard);

    @Query("select cbl from CommunityBoardLike cbl where cbl.who = :id")
    List<CommunityBoardLike> findByMemberId(String id);
}