package com.example.marketback.entity.chatting;

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

    @Column(length = 32)
    private String sender;

    @Column(length = 32)
    private String appointDate;

    @Column(length = 32)
    private String appointTime;
//
//    @Column(length = 32)
//    private String reminderTime;

//    @UpdateTimestamp
//    private Date updDate;

    public ChatRoom(Long roomNo, String appointDate, String appointTime){
        this.roomNo = roomNo;
        this.appointDate = appointDate;
        this.appointTime = appointTime;
    }
}
