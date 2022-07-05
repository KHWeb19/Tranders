package com.example.marketback.repository.jpa.community;

import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.jpa.community.CommunityComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Long> {
    List<CommunityComment> findCommunityCommentByCommunityBoard(CommunityBoard communityBoard);

    @Query("select cc from CommunityComment cc where cc.commentId = :commentId")
    CommunityComment findByCommentId(Long commentId);
}
