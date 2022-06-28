package com.example.marketback.service.near;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.response.BossMapResponse;

import java.util.List;

public interface NearService {
    List<BossMapResponse> showMap();

    void test();
}
