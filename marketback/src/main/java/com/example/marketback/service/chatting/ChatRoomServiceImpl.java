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

import javax.persistence.Column;
import javax.swing.text.html.Option;
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
    public void register(ChatRoom chatRoom, Long member1No, Long member2No, Long productNo) {
        Optional<ChatRoom> maybeChatRoom = chatRoomRepository.findChatRoomByMembers(Long.valueOf(member1No), Long.valueOf(member2No));
        Optional<ProductBoard> maybeProduct = productRepository.findById(Long.valueOf(productNo));
        ProductBoard boardEntity = productRepository.findByProductNo(productNo);
        //서로의 채팅방이 없다는게 확인되면 생성
        if (maybeChatRoom.equals(Optional.empty())) {
            Optional<Member> maybeMember1 = memberRepository.findById(Long.valueOf(member1No));
            Optional<Member> maybeMember2 = memberRepository.findById(Long.valueOf(member2No));
            chatRoom.setMember1(maybeMember1.get());
            chatRoom.setMember2(maybeMember2.get());
            chatRoom.setProductBoard(maybeProduct.get());
            boardEntity.setChatCnt(boardEntity.getChatCnt()+1);
            productRepository.save(boardEntity);
            chatRoomRepository.save(chatRoom);
        }
        //존재하면 게시물만 변경
        else {
            ChatRoom chatRoomEntity = new ChatRoom(
                    maybeChatRoom.get().getRoomNo(),
                    maybeChatRoom.get().getMember1(),
                    maybeChatRoom.get().getMember2(),
                    maybeProduct.get(),
                    maybeChatRoom.get().getAppointDate(),
                    maybeChatRoom.get().getAppointTime(),
                    maybeChatRoom.get().getLastMessage()
            );
            chatRoomRepository.save(chatRoomEntity);
            chatRoom.setProductBoard(maybeProduct.get());
        }
    }

    @Override
    public void bossRegister(ChatRoom chatRoom, Long member1No, Long member2No) {
        Optional<ChatRoom> maybeChatRoom = chatRoomRepository.findChatRoomByMembers(Long.valueOf(member1No), Long.valueOf(member2No));
        //서로의 채팅방이 없다는게 확인되면 생성
        if (maybeChatRoom.equals(Optional.empty())) {
            Optional<Member> maybeMember1 = memberRepository.findById(Long.valueOf(member1No));
            Optional<Member> maybeMember2 = memberRepository.findById(Long.valueOf(member2No));
            chatRoom.setMember1(maybeMember1.get());
            chatRoom.setMember2(maybeMember2.get());
            chatRoom.setProductBoard(null);
            chatRoomRepository.save(chatRoom);
        }
        //존재하면 게시물만 변경
        else {
            ChatRoom chatRoomEntity = new ChatRoom(
                    maybeChatRoom.get().getRoomNo(),
                    maybeChatRoom.get().getMember1(),
                    maybeChatRoom.get().getMember2(),
                    null,
                    maybeChatRoom.get().getAppointDate(),
                    maybeChatRoom.get().getAppointTime(),
                    maybeChatRoom.get().getLastMessage()
            );
            chatRoomRepository.save(chatRoomEntity);
        }
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

//    public ChatRoom move(Long member1, Long member2) {
//        Optional<ChatRoom> maybeChatRoom = chatRoomRepository.findChatRoomByMember(Long.valueOf(member1), Long.valueOf(member2));
//
//        if (maybeChatRoom.equals(Optional.empty())) {
//            //없으면 생성하는거 추가하자
//            return null;
//        }
//
//        return maybeChatRoom.get();
//    }


    @Override
    public void modify(ChatRoomRequest chatRoomRequest, Long roomNo) {
        Optional<ChatRoom> maybeChatRoom = chatRoomRepository.findById(Long.valueOf(roomNo));
        ChatRoom chatRoomEntity = new ChatRoom(
                roomNo,
                maybeChatRoom.get().getMember1(),
                maybeChatRoom.get().getMember2(),
                maybeChatRoom.get().getProductBoard(),
                chatRoomRequest.getAppointDate(),
                chatRoomRequest.getAppointTime(),
                maybeChatRoom.get().getLastMessage()
        );
        chatRoomRepository.save(chatRoomEntity);
    }

    @Override
    public void last(ChatRoomRequest chatRoomRequest, Long roomNo) {
        ChatRoom chatRoomEntity = chatRoomRepository.findByRoomNo(roomNo);
        chatRoomEntity.setLastMessage(chatRoomRequest.getLastMessage());
        chatRoomRepository.save(chatRoomEntity);
    }

    @Override
    public void charge(Member member) {
        Member memberEntity = memberRepository.findByMemberId(member.getId());
        memberEntity.setMoney(member.getMoney());
        memberRepository.save(memberEntity);
    }

    @Override
    public void pay(Member member, Long productNo) {
        //결제금액저장
        Member memberEntity = memberRepository.findByMemberId(member.getId());
        memberEntity.setMoney(member.getMoney());
        memberRepository.save(memberEntity);

        ProductBoard boardEntity = productRepository.findByProductNo(productNo);
        Member buyerEntity = memberRepository.findByMemberId(boardEntity.getMember().getId());

        //받은금액저장
        buyerEntity.setMoney(buyerEntity.getMoney()+boardEntity.getPrice());
        memberRepository.save(buyerEntity);

        //구매자저장, 판매상태변경
        boardEntity.setBuyer(memberEntity);
        boardEntity.setProcess("판매완료");
        productRepository.save(boardEntity);
    }
}
