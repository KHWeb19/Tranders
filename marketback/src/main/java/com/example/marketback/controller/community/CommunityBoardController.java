package com.example.marketback.controller.community;

import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.near.Near;
import com.example.marketback.repository.jpa.community.CommunityBoardRepository;
import com.example.marketback.service.jpa.community.CommunityBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/communityboard")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class CommunityBoardController {

    @Autowired
    private CommunityBoardService service;

    @Autowired
    private CommunityBoardRepository repository;

    @PostMapping("/register")
    public void communityBoardRegister (@Validated CommunityBoard board, @Validated Near near, @RequestParam(required = false) List<MultipartFile> file) throws Exception {
        log.info("communityBoardRegister();" + file);

        service.register(board, near, file);
    }

    @GetMapping("/list/{memberNo}")
    public List<CommunityBoard> communityBoardList (
            @PathVariable("memberNo") Long memberNo
    ) {
        log.info("communityBoardList()");
        log.info(String.valueOf(memberNo));

        return service.list(memberNo);
    }

    @GetMapping("/{boardNo}")
    public CommunityBoard communityBoardRead (
            @PathVariable("boardNo") Integer boardNo) {
        log.info("communityBoardRead()");

        return service.read(boardNo);
    }

    @PutMapping("/boardModify")
    public CommunityBoard communityBoardModify (
            CommunityBoard board, @RequestParam(required = false) List<MultipartFile> file) throws Exception  {

        service.modify(board,file);

        return board;
    }

    @DeleteMapping("/{boardNo}")
    public void communityBoardRemove(
            @PathVariable("boardNo") Integer boardNo, CommunityBoard board) throws Exception  {
        log.info("communityBoardRemove()");

        Optional<CommunityBoard> selectFile = repository.findById(Long.valueOf(boardNo));
        CommunityBoard deleteFile = selectFile.get();

        if (deleteFile.getFileName1() != null) {
            Path filePath = Paths.get("D:\\khweb19\\Tranders\\marketfront\\src\\assets\\uploadImg\\community\\" + deleteFile.getFileName1());
            Files.delete(filePath);
        }

        if (deleteFile.getFileName2() != null) {
            Path filePath = Paths.get("D:\\khweb19\\Tranders\\marketfront\\src\\assets\\uploadImg\\community\\" + deleteFile.getFileName2());
            Files.delete(filePath);
        }
        if (deleteFile.getFileName3() != null) {
            Path filePath = Paths.get("D:\\khweb19\\Tranders\\marketfront\\src\\assets\\uploadImg\\community\\" + deleteFile.getFileName3());
            Files.delete(filePath);
        }
        if (deleteFile.getFileName4() != null) {
            Path filePath = Paths.get("D:\\khweb19\\Tranders\\marketfront\\src\\assets\\uploadImg\\community\\" + deleteFile.getFileName4());
            Files.delete(filePath);
        }
        if (deleteFile.getFileName5() != null) {
            Path filePath = Paths.get("D:\\khweb19\\Tranders\\marketfront\\src\\assets\\uploadImg\\community\\" + deleteFile.getFileName5());
            Files.delete(filePath);
        }
        if (deleteFile.getFileName6() != null) {
            Path filePath = Paths.get("D:\\khweb19\\Tranders\\marketfront\\src\\assets\\uploadImg\\community\\" + deleteFile.getFileName6());
            Files.delete(filePath);
        }

        if (deleteFile.getFileName7() != null) {
            Path filePath = Paths.get("D:\\khweb19\\Tranders\\marketfront\\src\\assets\\uploadImg\\community\\" + deleteFile.getFileName7());
            Files.delete(filePath);
        }
        if (deleteFile.getFileName8() != null) {
            Path filePath = Paths.get("D:\\khweb19\\Tranders\\marketfront\\src\\assets\\uploadImg\\community\\" + deleteFile.getFileName8());
            Files.delete(filePath);
        }
        if (deleteFile.getFileName9() != null) {
            Path filePath = Paths.get("D:\\khweb19\\Tranders\\marketfront\\src\\assets\\uploadImg\\community\\" + deleteFile.getFileName9());
            Files.delete(filePath);
        }
        if (deleteFile.getFileName10() != null) {
            Path filePath = Paths.get("D:\\khweb19\\Tranders\\marketfront\\src\\assets\\uploadImg\\community\\" + deleteFile.getFileName10());
            Files.delete(filePath);
        }

        service.remove(boardNo);

    }

}