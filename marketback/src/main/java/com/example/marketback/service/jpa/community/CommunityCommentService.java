package com.example.marketback.service.jpa.community;

import com.example.marketback.entity.jpa.community.CommunityComment;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommunityCommentService {
    public void register (Integer boardNo, CommunityComment communityComment,@RequestParam(required = false) MultipartFile file) throws Exception;
    public void modify (Long commentId, Long boardNo, String comment, String placeName, String placeUrl,  @RequestParam(required = false) MultipartFile file) throws Exception;
    public List<CommunityComment> list (Integer boardNo);
    public void delete (Long commentId);
}
