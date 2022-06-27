package com.example.marketback.controller.community;

import com.example.marketback.service.jpa.community.CommunityBoardLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/communityboard")
@CrossOrigin(origins ="http://localhost:8080", allowedHeaders = "*")
public class CommunityBoardLikeController {

    @Autowired
    private CommunityBoardLikeService likeService;

    @PostMapping("/{boardNo}/like")
    public boolean addLike (@RequestBody String who, @PathVariable("boardNo") Long boardNo ) {
        log.info ("like board !" + who + boardNo);
        return likeService.addLike(who, boardNo);
    }
}