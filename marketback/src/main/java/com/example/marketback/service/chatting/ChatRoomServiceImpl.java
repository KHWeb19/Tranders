package com.example.marketback.service.chatting;

import com.example.marketback.controller.chatting.ChatRoomRequest;
import com.example.marketback.entity.chatting.ChatRoom;
import com.example.marketback.entity.member.Member;
import com.example.marketback.repository.chatting.ChatRoomRepository;
import com.example.marketback.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ChatRoomServiceImpl implements ChatRoomService{

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void register(ChatRoom chatRoom, Long member1No) {
        //member2No로 회원찾아서 member2에 set
        //이미 있으면 생성 no
        Optional<Member> maybeMember = memberRepository.findById(Long.valueOf(member1No));
        chatRoom.setMember1(maybeMember.get());
        chatRoomRepository.save(chatRoom);
    }

    @Override
    public List<ChatRoom> list(Long memberNo) {
        return chatRoomRepository.findChatRoomByMemberNo(Long.valueOf(memberNo));
    }

    public ChatRoom read(Long roomNo) {
        Optional<ChatRoom> maybeChatRoom = chatRoomRepository.findById(Long.valueOf(roomNo));

        if (maybeChatRoom.equals(Optional.empty())) {
            return null;
        }

        return maybeChatRoom.get();
    }

    public ChatRoom move(Long member1, Long member2) {
        Optional<ChatRoom> maybeChatRoom = chatRoomRepository.findChatRoomByMember(Long.valueOf(member1), Long.valueOf(member2));

        if (maybeChatRoom.equals(Optional.empty())) {
            //없으면 생성하는거 추가하자
            return null;
        }

        return maybeChatRoom.get();
    }

    @Override
    public void modify(ChatRoomRequest chatRoomRequest, Long roomNo) {
        Optional<ChatRoom> maybeChatRoom = chatRoomRepository.findById(Long.valueOf(roomNo));
        ChatRoom chatRoomEntity = new ChatRoom(
                roomNo,
                maybeChatRoom.get().getMember1(),
                maybeChatRoom.get().getMember2(),
                chatRoomRequest.getAppointDate(),
                chatRoomRequest.getAppointTime()
        );
        chatRoomRepository.save(chatRoomEntity);
    }
}
