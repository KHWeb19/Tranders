package com.example.marketback.service.follow;

import com.example.marketback.entity.follow.Follow;
import com.example.marketback.entity.productBoard.ProductBoard;

import java.util.List;

public interface FollowService {
    boolean register (Long memberNo, Long loginNo, Follow follow);
    boolean onFollow (Long loginNo, Long memberNo);
    List<Follow> followList (Long memberNo);
    List<ProductBoard> followBoardList (Long memberNo);
}
