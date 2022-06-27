package com.example.marketback.repository.jpa.community;

import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.jpa.community.CommunityBoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityBoardLikeRepository extends JpaRepository<CommunityBoardLike, Long> {
    Optional<CommunityBoardLike> findByWhoAndCommunityBoard (String who, CommunityBoard communityBoard);
}