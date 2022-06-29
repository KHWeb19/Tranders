package com.example.marketback.repository.jpa.community;

import com.example.marketback.entity.jpa.community.CommunityBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

public interface CommunityBoardRepository extends JpaRepository<CommunityBoard, Long> {

    List<CommunityBoard> findByContentContaining(String keyWord);

    @Transactional
    @Modifying
    @Query(value = "update CommunityBoard lc set lc.likeCount = lc.likeCount + 1 where lc.boardNo = :boardNo",nativeQuery = true)
    void updateLikeCount(Long boardNo);


}

