package com.example.marketback.repository.jpa.community;

import com.example.marketback.entity.jpa.community.CommunityBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CommunityBoardRepository extends JpaRepository<CommunityBoard, Long> {

    List<CommunityBoard> findByContentContaining(String keyWord);

    @Transactional
    @Modifying
    @Query(value = "update CommunityBoard lc set lc.likeCount = lc.likeCount + 1 where lc.boardNo = :boardNo",nativeQuery = true)
    void updateLikeCount(Long boardNo);

    @Query("select cb from CommunityBoard cb where cb.near.nearNo = :nearNo")
    List<CommunityBoard> findTopByBossNoOrderByIdDesc(@Param("nearNo")Long nearNo);

    @Query("select cb from CommunityBoard cb where cb.boss.bossAuthNo = :bossNo")
    List<CommunityBoard> findByBossNo(@Param("bossNo")Long bossNo);

    @Query("select cb from CommunityBoard cb where cb.near.nearNo = :nearNo")
    List<CommunityBoard> findByNearNo(@Param("nearNo")Long nearNo);

    @Query("select cb from CommunityBoard cb where cb.boardNo = :commentId")
    CommunityBoard findByCommentId(@Param("commentId")Long commentId);

    @Query("select cb from CommunityBoard cb where cb.member.city.villageName  = :region and cb.content like %:keyWord% and cb.title like %:keyWord%")
    List<CommunityBoard> findAllByVillageNameContentContain(String keyWord, String region, Pageable pageable);

    @Query("select cb from CommunityBoard cb where cb.member.city.district  = :region and cb.content like %:keyWord% and cb.title like %:keyWord%")
    List<CommunityBoard> findAllByDistrictContentContain(String keyWord, String region, Pageable pageable);

    @Query("select cb from CommunityBoard cb where cb.member.city.city  = :region and cb.content like %:keyWord% and cb.title like %:keyWord%")
    List<CommunityBoard> findAllByCityContentContain(String keyWord, String region, Pageable pageable);

    @Query("select cb from CommunityBoard cb where cb.boardNo = :boardNo")
    CommunityBoard findByBoardNo(@Param("boardNo") Long boardNo);

    @Query("select cb from CommunityBoard cb where cb.member.city.villageName = :region order by boardNo desc")
    List<CommunityBoard> findByVillageNameMemberContain(@Param("region") String region);

    @Query("select cb from CommunityBoard cb where cb.member.city.district = :region order by boardNo desc")
    List<CommunityBoard> findByDistrictMemberContain(@Param("region") String region);

    @Query("select cb from CommunityBoard cb where cb.member.city.city = :region order by boardNo desc")
    List<CommunityBoard> findByCityMemberContain(@Param("region") String region);

    @Query("select cb from CommunityBoard cb where cb.member.id = :id")
    List<CommunityBoard> findByMemberId(String id);
}

