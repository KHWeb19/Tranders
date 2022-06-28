package com.example.marketback.service.near;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.repository.boss.BossRepository;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.response.BossMapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NearServiceImpl implements NearService{

    @Autowired
    private BossRepository bossRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<BossMapResponse> showMap() {
        List<Boss> bossEntity = bossRepository.findAll();

        List<BossMapResponse> responses = new ArrayList<>();

        for (Boss boss : bossEntity) {
            responses.add(new BossMapResponse(boss.getPlaceName(), boss.getLat(), boss.getLng()));
        }

        return responses;
    }

    @Override
    public void test() {
        String[] placeName = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};

        for (int i = 0; i < 10; i++) {
            float x = (float) (37.5642135f + (0.005 * i));
            float y = (float) (127.0016985f + (0.005 * i));

            Boss boss = new Boss(placeName[i], ""+x, ""+y);

            bossRepository.save(boss);
        }

    }
}
