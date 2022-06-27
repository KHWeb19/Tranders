package com.example.marketback.service.jpa.community;

import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.jpa.community.CommunityBoardLike;
import com.example.marketback.repository.jpa.community.CommunityBoardLikeRepository;
import com.example.marketback.repository.jpa.community.CommunityBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityBoardLikeService {
    @Autowired
    private CommunityBoardLikeRepository likeRepository;

    @Autowired
    private CommunityBoardRepository boardRepository;

    public boolean addLike (String who, Long boardNo) {
        CommunityBoard communityBoard = boardRepository.findById(boardNo).orElseThrow();

        // 중복 좋아요 방지
        if (likeRepository.findByWhoAndCommunityBoard(who, communityBoard).isEmpty()) {
            likeRepository.save(new CommunityBoardLike(who, communityBoard));
            return true;
        }
        return false;
    }
}


