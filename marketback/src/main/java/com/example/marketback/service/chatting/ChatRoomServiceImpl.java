package com.example.marketback.service.chatting;

import com.example.marketback.controller.chatting.ChatRoomRequest;
import com.example.marketback.entity.chatting.ChatRoom;
import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.repository.chatting.ChatRoomRepository;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.repository.productBoard.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ChatRoomServiceImpl implements ChatRoomService{

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void register(ChatRoom chatRoom, Long member1No, Long productNo) {
        //이미 있으면 생성 no
        Optional<Member> maybeMember = memberRepository.findById(Long.valueOf(member1No));
        Optional<ProductBoard> maybeProduct = productRepository.findById(Long.valueOf(productNo));
        chatRoom.setMember1(maybeMember.get());
        chatRoom.setProductBoard(maybeProduct.get());
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
