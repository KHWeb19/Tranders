package com.example.marketback.repository.chatting;

import com.example.marketback.entity.chatting.ChatRoom;
import com.example.marketback.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("select c from ChatRoom c join c.member1 cm join c.productBoard.member cpm where cm.memberNo = :memberNo or cpm.memberNo = :memberNo order by c.roomNo desc ")
    List<ChatRoom> findChatRoomByMemberNo(@Param("memberNo") Long memberNo);

    //서로의 채팅방이 존재하는지
    @Query("select c from ChatRoom c join c.member1 cm1 join c.member2 cm2 where (cm1.memberNo = :member1No and cm2.memberNo = :member2No) or (cm1.memberNo = :member2No and cm2.memberNo = :member1No)")
    Optional<ChatRoom> findChatRoomByMembers(@Param("member1No") Long member1No, @Param("member2No") Long member2No);
//    @Query("select c from ChatRoom c where c.member1 = :member1 and c.member2 = :member2")
//    Optional<ChatRoom> findChatRoomByMember(@Param("member1") Long member1, @Param("member2") Long member2);
}
