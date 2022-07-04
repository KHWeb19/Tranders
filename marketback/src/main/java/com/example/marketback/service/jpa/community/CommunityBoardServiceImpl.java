package com.example.marketback.service.jpa.community;

import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.near.Near;
import com.example.marketback.repository.jpa.community.CommunityBoardRepository;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.repository.near.NearRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.*;

@Slf4j
@Service
public class CommunityBoardServiceImpl implements CommunityBoardService {

    @Autowired
    CommunityBoardRepository repository;

    @Autowired
    private NearRepository nearRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void register(CommunityBoard board, Near near, @RequestParam(required = false) List<MultipartFile> file) throws Exception {

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

        System.out.println(near.getStoreRegion());

        Member memberEntity = memberRepository.findByMemberId(board.getWriter());
        board.setWriter(memberEntity.getName());
        board.setRegion(memberEntity.getRegion());
        board.setMember(memberEntity);

        if(!Objects.equals(near.getStoreRegion(), "null")) {
            Optional<Near> nearOptional = nearRepository.findByAddress(near.getStoreRegion());

            if (nearOptional.isEmpty()) {
                System.out.println("없다");
                String category = near.getCategory().substring(near.getCategory().lastIndexOf("> ") + 2);
                String[] region = near.getStoreRegion().split(" ");

                near.setCategory(category);
                near.setAddress(near.getStoreRegion());
                near.setStoreRegion(region[2]);
                near.setMarketHomePage(board.getPlaceUrl());
                near.setReviewCount(0);
                near.setCommunityCount(1);
            } else {
                near = nearOptional.get();
                near.setCommunityCount(near.getCommunityCount() + 1);
            }
            nearRepository.save(near);
        }else {
            near = null;
        }

        board.setNear(near);
        repository.save(board);
    }

    @Override
    public List<CommunityBoard> list() {
        log.info("list");

        List<CommunityBoard> communityBoardList = repository.findAll(Sort.by(Sort.Direction.DESC, "boardNo"));

        List<CommunityBoard> response = new ArrayList<>();

        for(CommunityBoard communityBoard : communityBoardList){
            communityBoard.setNear(null);
            response.add(communityBoard);
        }
        return response;
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
            //repository.save(communityBoard);
            communityBoard.setNear(null);
            return communityBoard;
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