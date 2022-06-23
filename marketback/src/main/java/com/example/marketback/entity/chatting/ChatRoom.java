package com.example.marketback.entity.chatting;

import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.productBoard.ProductBoard;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "chatroom")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_no")
    private Long roomNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Member member1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private ProductBoard productBoard;

    //member와 조인
    @Column
    private Long member2;

    @Column(length = 32)
    private String appointDate;

    @Column(length = 32)
    private String appointTime;
//
//    @Column(length = 32)
//    private String reminderTime;

//    @UpdateTimestamp
//    private Date updDate;
//
    public ChatRoom(Long roomNo, Member member1, Long member2, String appointDate, String appointTime){
        this.roomNo = roomNo;
        this.member1 = member1;
        this.member2 = member2;
        this.appointDate = appointDate;
        this.appointTime = appointTime;
    }
}
