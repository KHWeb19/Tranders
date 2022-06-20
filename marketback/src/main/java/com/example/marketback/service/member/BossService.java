package com.example.marketback.service.member;

import com.example.marketback.entity.boss.Boss;

public interface BossService {
    boolean checkBossMember(String id);

    void register(String fileSrc, String id, String name, String region, String category);

    Boss getPage(Long memberNo);
}
