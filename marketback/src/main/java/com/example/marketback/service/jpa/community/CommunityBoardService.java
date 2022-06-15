package com.example.marketback.service.jpa.community;

import com.example.marketback.entity.jpa.community.CommunityBoard;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommunityBoardService {
    public void register (CommunityBoard board, @RequestParam(required = false) List<MultipartFile> file) throws Exception;
    public List<CommunityBoard> list ();
    public CommunityBoard read (Integer boardNo);
    public void modify (CommunityBoard board);
    public void remove (Integer boardNo);
}
