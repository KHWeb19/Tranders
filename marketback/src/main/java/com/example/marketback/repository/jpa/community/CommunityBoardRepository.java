package com.example.marketback.repository.jpa.community;

import com.example.marketback.entity.jpa.community.CommunityBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommunityBoardRepository extends JpaRepository<CommunityBoard, Long> {
}

