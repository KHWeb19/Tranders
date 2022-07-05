package com.example.marketback.controller.community;

import com.example.marketback.entity.jpa.community.CommunityComment;
import com.example.marketback.service.jpa.community.CommunityCommentService;
import com.example.marketback.service.jpa.community.CommunityCommentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/communityboard")
@CrossOrigin(origins ="http://localhost:8080", allowedHeaders = "*")

public class CommunityCommentController {
    @Autowired
    private CommunityCommentService service;

    @PostMapping("/{boardNo}/comment/register")
    public void register (@Validated CommunityComment communityComment, @PathVariable("boardNo") Integer boardNo, @RequestParam(required = false) MultipartFile file) throws Exception {
        log.info("Comment register()" + communityComment);
        service.register(boardNo, communityComment, file);
    }

    @PutMapping("/{boardNo}/{commentId}/comment/register")
    public void modify (@RequestParam("comment") String comment, @PathVariable("commentId") Long commentId, @PathVariable("boardNo") Long boardNo, @RequestParam(required = false) MultipartFile file) throws Exception {

        log.info("Comment modify(): ");

//        return CommunityCommentServiceImpl.modify(commentId, communityComment);
        service.modify(commentId, boardNo, comment, file);
    }

    @GetMapping("/{boardNo}/comment/list")
    public List<CommunityComment> read (@PathVariable("boardNo") Integer boardNo){
        log.info("comment List");
        return service.list(boardNo);
    }

    @DeleteMapping("/comment/{commentId}")
    public void delete (@PathVariable ("commentId") Long commentId , CommunityComment communityComment){
        log.info("Comment remove()");
        service.delete(commentId);
    }
}