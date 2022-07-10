package com.example.marketback.repository.follow;

import com.example.marketback.entity.chatting.ChatRoom;
import com.example.marketback.entity.follow.Follow;
import com.example.marketback.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByMyAndYour (Member my, Member your);

    @Query("select f from Follow f join f.my fm where fm.memberNo = :memberNo")
    List<Follow> findByLoginNo(@Param("memberNo") Long memberNo);

}
