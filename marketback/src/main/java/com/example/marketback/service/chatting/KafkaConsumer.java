//package com.example.marketback.service.chatting;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.List;
//
//@Service
//public class KafkaConsumer {
//
//    @KafkaListener(topics = "test3", groupId = "foo")
//    public void consume(String message) throws IOException {
//        System.out.println(String.format("Consumed message : %s", message));
//    }
//}