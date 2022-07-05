package com.example.marketback.service.near;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.response.BossMapResponse;
import com.example.marketback.response.NearPageResponse;
import com.example.marketback.response.NearReviewResponse;
import com.example.marketback.response.NoBossMapResponse;

import java.util.List;

public interface NearService {
    List<BossMapResponse> showMap();

    void test();

    Boss returnBoss(Long bossNo);

    List<NearReviewResponse> nearReview();

    List<NoBossMapResponse> communityMapList();

    List<NearReviewResponse> communityBoard();

    NearPageResponse showNearPage(Long nearNo);
}
