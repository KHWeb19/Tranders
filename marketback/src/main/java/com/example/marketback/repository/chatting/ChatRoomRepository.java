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

    //생성 후 방으로 바로됨 이동하려고 만든건데 잘 안
    @Query("select c from ChatRoom c where c.member1 = :member1 and c.member2 = :member2")
    Optional<ChatRoom> findChatRoomByMember(@Param("member1") Long member1, @Param("member2") Long member2);
}
