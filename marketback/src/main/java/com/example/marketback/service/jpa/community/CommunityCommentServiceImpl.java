package com.example.marketback.service.jpa.community;

import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.jpa.community.CommunityComment;
import com.example.marketback.repository.jpa.community.CommunityBoardRepository;
import com.example.marketback.repository.jpa.community.CommunityCommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CommunityCommentServiceImpl implements CommunityCommentService {
    @Autowired
    CommunityBoardRepository boardRepository;

    @Autowired
    CommunityCommentRepository repository;

    @Override
    public void register(Integer boardNo, CommunityComment communityComment, @RequestParam(required = false) MultipartFile file) throws Exception {

        Optional<CommunityBoard> boardItem = boardRepository.findById(Long.valueOf(boardNo));
        communityComment.setCommunityBoard(boardItem.get());

        if (file != null) {
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            FileOutputStream saveFile = new FileOutputStream("../marketfront/src/assets/uploadImg/community/" + fileName);

            saveFile.write(file.getBytes());
            saveFile.close();

            communityComment.setFileName(fileName);
        }
        repository.save(communityComment);
    }

    @Override
    public void modify(Long commentId, Long boardNo, String comment, String placeName, String placeUrl, MultipartFile file) throws Exception {
        CommunityComment boardItem = repository.findByCommentId(commentId);
        boardItem.setComment(comment);
        boardItem.setPlaceName(placeName);
        boardItem.setPlaceUrl(placeUrl);

        if (boardItem.getFileName().equals(Optional.empty())) {
            Path filePath = Paths.get("../marketfront/src/assets/uploadImg/community/" + boardItem.getFileName());
            Files.delete(filePath);
        }
        if (file != null) {
            System.out.println("=================");
            System.out.println("modify");
            System.out.println("=================");
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            FileOutputStream saveFile = new FileOutputStream("../marketfront/src/assets/uploadImg/community/" + fileName);
            System.out.println("=================");
            System.out.println(fileName);
            System.out.println("=================");
            saveFile.write(file.getBytes());
            saveFile.close();

            boardItem.setFileName(fileName);
        }


        repository.save(boardItem);
        //        communityComment.setCommunityBoard(boardItem.get());

        //pass


//                if (file != null) {
//                    UUID uuid = UUID.randomUUID();
//                    String fileName = uuid + "_" + file.getOriginalFilename();
//                    FileOutputStream saveFile = new FileOutputStream("../marketfront/src/assets/uploadImg/community/" + fileName);
//
//                    saveFile.write(file.getBytes());
//                    saveFile.close();
//
//                    communityComment.setFileName(fileName);
//                }

        //        communityComment.setCommentId(Long.valueOf(commentId));
        //
        //        repository.save(communityComment);

        //        return communityComment;
    }

    @Override
    public List<CommunityComment> list(Integer boardNo) {
        CommunityBoard communityBoard = boardRepository.findById(Long.valueOf(boardNo)).get();
        return repository.findCommunityCommentByCommunityBoard(communityBoard);
    }


    @Override
    public void delete(Long commentId) {
        repository.deleteById(Long.valueOf(commentId));
    }
}