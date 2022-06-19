package com.example.marketback.repository.chatting;

import com.example.marketback.entity.chatting.ChatRoom;
import com.example.marketback.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("select c from ChatRoom c join c.member1 cm where cm.memberNo = :memberNo")
    List<ChatRoom> findChatRoomByMemberNo(@Param("memberNo") Long memberNo);
}
