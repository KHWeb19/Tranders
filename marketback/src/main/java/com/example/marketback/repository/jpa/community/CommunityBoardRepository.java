package com.example.marketback.repository.jpa.community;

import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.review.BossReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

public interface CommunityBoardRepository extends JpaRepository<CommunityBoard, Long> {

    List<CommunityBoard> findByContentContaining(String keyWord);

    @Transactional
    @Modifying
    @Query(value = "update CommunityBoard lc set lc.likeCount = lc.likeCount + 1 where lc.boardNo = :boardNo",nativeQuery = true)
    void updateLikeCount(Long boardNo);

//    @Query(value = "update CommunityBoard cb set lc.likeCount = lc.likeCount + 1 where lc.boardNo = :boardNo",nativeQuery = true)
//    CommunityBoard findByBoardNo(Long boardNo);


    @Query("select cb from CommunityBoard cb where cb.near.nearNo = :nearNo")
    List<CommunityBoard> findTopByBossNoOrderByIdDesc(Long nearNo);

    @Query("select cb from CommunityBoard cb where cb.boss.bossAuthNo = :bossNo")
    List<CommunityBoard> findByBossNo(Long bossNo);

    @Query("select cb from CommunityBoard cb where cb.near.nearNo = :nearNo")
    List<CommunityBoard> findByNearNo(Long nearNo);

    @Query("select cb from CommunityBoard cb where cb.boardNo = :commentId")
    CommunityBoard findByCommentId(Long commentId);

    @Query("select cb from CommunityBoard cb where cb.boardNo = :boardNo")
    CommunityBoard findByBoardNo(@Param("boardNo") Long boardNo);
}

