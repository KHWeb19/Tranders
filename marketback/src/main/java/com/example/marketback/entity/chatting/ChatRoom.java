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
    private Member member2;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private ProductBoard productBoard;

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
    public ChatRoom(Long roomNo, Member member1, Member member2, ProductBoard productBoard, String appointDate, String appointTime){
        this.roomNo = roomNo;
        this.member1 = member1;
        this.member2 = member2;
        this.productBoard = productBoard;
        this.appointDate = appointDate;
        this.appointTime = appointTime;
    }
}
