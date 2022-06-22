package com.example.marketback.service.member;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.boss.BossImage;
import com.example.marketback.request.BossMarketInfoRequest;
import com.example.marketback.response.BossBackProfileImg;

import java.util.List;

public interface BossService {
    boolean checkBossMember(String id);

    void register(String fileSrc, String id, String name, String region, String category);

    Boss getPage(Long memberNo);

    void saveMarketInfo(BossMarketInfoRequest bossMarketInfoRequest);

    void saveBackImg(String id, String name, List<String> fileName);

    void modifyProfile(String fileSrc, String id);

    List<BossBackProfileImg> getBackProfile(String id);
}
