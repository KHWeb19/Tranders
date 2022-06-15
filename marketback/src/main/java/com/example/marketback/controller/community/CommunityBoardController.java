package com.example.marketback.controller.community;

import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.service.jpa.community.CommunityBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/communityboard")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class CommunityBoardController {

    @Autowired
    private CommunityBoardService service;

    @PostMapping("/register")
    public void communityBoardRegister (@Validated CommunityBoard board, @RequestParam(required = false) List<MultipartFile> file) throws Exception {
        log.info("communityBoardRegister();" + file);

        service.register(board,file);
    }

    @GetMapping("/list")
    public List<CommunityBoard> communityBoardList () {
        log.info("communityBoardList()");

        return service.list();
    }

    @GetMapping("/{boardNo}")
    public CommunityBoard communityBoardRead (
            @PathVariable("boardNo") Integer boardNo) {
        log.info("communityBoardRead()");

        return service.read(boardNo);
    }

    @PutMapping("/{boardNo}")
    public CommunityBoard communityBoardModify (
            @PathVariable("boardNo") Integer boardNo,
            @RequestBody CommunityBoard communityBoard) {
        log.info("communityBoardModify(): " + communityBoard);

        communityBoard.setBoardNo(Long.valueOf(boardNo));
        service.modify(communityBoard);

        return communityBoard;
    }

    @DeleteMapping("/{boardNo}")
    public void communityBoardRemove (
            @PathVariable("boardNo") Integer boardNo) {
        log.info("communityBoardRemove()");

        service.remove(boardNo);
    }
}
