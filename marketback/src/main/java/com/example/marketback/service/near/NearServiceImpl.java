package com.example.marketback.service.near;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.review.BossReview;
import com.example.marketback.repository.boss.BossRepository;
import com.example.marketback.repository.boss.bossReview.BossReviewImageRepository;
import com.example.marketback.repository.boss.bossReview.BossReviewRepository;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.response.BossMapResponse;
import com.example.marketback.response.NearReviewResponse;
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

    @Autowired
    private BossReviewRepository bossReviewRepository;

    @Autowired
    private BossReviewImageRepository bossReviewImageRepository;

    @Override
    public List<BossMapResponse> showMap() {
        List<Boss> bossEntity = bossRepository.findAll();

        List<BossMapResponse> responses = new ArrayList<>();

        for (Boss boss : bossEntity) {
            responses.add(new BossMapResponse(boss.getBossAuthNo(), boss.getPlaceName(), boss.getLat(), boss.getLng(), boss.getRegion(), boss.getCategory(), boss.getReviewCount()));
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

    @Override
    public Boss returnBoss(Long bossNo) {
        return bossRepository.findByBossNo(bossNo);
    }

    @Override
    public List<NearReviewResponse> nearReview() {
        List<Boss> bossEntity = bossRepository.findAll();
        List<NearReviewResponse> responses = new ArrayList<>();
        System.out.println(bossEntity.size());

        for(Boss boss : bossEntity) {
            if(boss.getReviewCount() == 0) {
                responses.add(null);
            }else {
                List<BossReview> bossReview = bossReviewRepository.findTopByBossNoOrderByIdDesc(boss.getBossAuthNo());
                responses.add(new NearReviewResponse(bossReview.get(0).getMember().getName(), bossReview.get(0).getMember().getRegion(), bossReview.get(0).getContent(), bossReview.get(0).getMember().getProfileImg(), bossReview.get(0).getState()));
            }
        }
        return responses;
    }
}