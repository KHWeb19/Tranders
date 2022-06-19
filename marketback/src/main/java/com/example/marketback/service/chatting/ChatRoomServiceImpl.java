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
    public void register(ChatRoom chatRoom, Long memberNo) {
        Optional<Member> maybeMember = memberRepository.findById(Long.valueOf(memberNo));
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

    @Override
    public void modify(ChatRoomRequest chatRoomRequest, Long roomNo) {
        Optional<ChatRoom> maybeChatRoom = chatRoomRepository.findById(Long.valueOf(roomNo));
        ChatRoom chatRoomEntity = new ChatRoom(
                roomNo,
                maybeChatRoom.get().getMember1(),
                chatRoomRequest.getAppointDate(),
                chatRoomRequest.getAppointTime()
        );
        chatRoomRepository.save(chatRoomEntity);
    }
}
