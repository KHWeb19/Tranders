package com.example.marketback.service.jpa.community;

import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.repository.jpa.community.CommunityBoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CommunityBoardServiceImpl implements CommunityBoardService {

    @Autowired
    CommunityBoardRepository repository;

    @Override
    public void register(CommunityBoard board, @RequestParam(required = false) List<MultipartFile> file) throws Exception {

        try {
            if (file != null) {
                for (MultipartFile multipartFile : file) {

                    log.info("requestUploadFile() - Make file: " +
                            multipartFile.getOriginalFilename());

                    UUID uuid = UUID.randomUUID();  //파일 이름 중복 방지
                    String fileName = uuid + "_" + multipartFile.getOriginalFilename();
                    FileOutputStream saveFile = new FileOutputStream("../marketfront/src/assets/uploadImg/community/" + fileName);

                    saveFile.write(multipartFile.getBytes());
                    saveFile.close();

                    if (multipartFile == file.get(0)) {
                        board.setFileName1(fileName);
                    } else if (multipartFile == file.get(1)){
                        board.setFileName2(fileName);
                    } else if (multipartFile == file.get(2)) {
                        board.setFileName3(fileName);
                    } else if (multipartFile == file.get(3)) {
                        board.setFileName4(fileName);
                    } else if ( multipartFile == file.get(4)){
                        board.setFileName5(fileName);
                    } else if (multipartFile == file.get(5)) {
                        board.setFileName6(fileName);
                    } else if (multipartFile == file.get(6)) {
                        board.setFileName7(fileName);
                    } else if ( multipartFile == file.get(7)){
                        board.setFileName8(fileName);
                    } else if (multipartFile == file.get(8)) {
                        board.setFileName9(fileName);
                    } else {
                        board.setFileName10(fileName);
                    }
                }
            }
        } catch (Exception e) {
            log.info("Upload Fail!!!");
        }
        log.info("requestUploadFile(): Success!!!");
        repository.save(board);
    }

    @Override
    public List<CommunityBoard> list() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "boardNo"));
    }

    @Override
    public CommunityBoard read(Integer boardNo) {
        Optional<CommunityBoard> maybeReadBoard = repository.findById(Long.valueOf(boardNo));

        if (maybeReadBoard.equals(Optional.empty())) {
            log.info("Can't read board!");
            return null;
        } else {
            CommunityBoard communityBoard = maybeReadBoard.get();
//            communityBoard.increaseViewCnt();
            repository.save(communityBoard);
            return maybeReadBoard.get();
        }
    }

    @Override
    public void modify(CommunityBoard board, @RequestParam(required = false) List<MultipartFile> file) throws Exception {

        try {
            if (file != null) {
                for (MultipartFile multipartFile : file) {
                    UUID uuid = UUID.randomUUID();
                    String fileName = uuid + "_" + multipartFile.getOriginalFilename();
                    FileOutputStream saveFile = new FileOutputStream("../marketfront/src/assets/uploadImg/community/" + fileName);

                    saveFile.write(multipartFile.getBytes());
                    saveFile.close();

                    if (multipartFile == file.get(0)) {
                        board.setFileName1(fileName);
                    } else if ( multipartFile == file.get(1)){
                        board.setFileName2(fileName);
                    } else if (multipartFile == file.get(2)) {
                        board.setFileName3(fileName);
                    } else if (multipartFile == file.get(3)) {
                        board.setFileName4(fileName);
                    } else if (multipartFile == file.get(4)) {
                        board.setFileName5(fileName);
                    } else if (multipartFile == file.get(5)) {
                        board.setFileName6(fileName);
                    } else if (multipartFile == file.get(6)) {
                        board.setFileName7(fileName);
                    } else if ( multipartFile == file.get(7)){
                        board.setFileName8(fileName);
                    } else if (multipartFile == file.get(8)) {
                        board.setFileName9(fileName);
                    } else {
                        board.setFileName10(fileName);
                    }

                }
            }
        } catch (Exception e) {
            log.info("Upload Fail!!!");
        }
        repository.save(board);
    }

    @Override
    public void remove(Integer boardNo) throws Exception  {
        repository.deleteById(Long.valueOf(boardNo));
    }

    @Override
    public List<CommunityBoard> searchList(String keyWord) {
        List<CommunityBoard> findSearchList = repository.findByContentContaining(keyWord);

        return findSearchList;
    }

}