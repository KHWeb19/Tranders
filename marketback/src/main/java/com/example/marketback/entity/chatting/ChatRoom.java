package com.example.marketback.entity.chatting;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "chat_room")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long roomNo;

    @Column(length = 32)
    private String appointTime;

    @Column(length = 32)
    private String reminderTime;

    @UpdateTimestamp
    private Date updDate;
}
