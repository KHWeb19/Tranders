package com.example.marketback.service.near;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.near.Near;
import com.example.marketback.entity.review.BossReview;
import com.example.marketback.repository.boss.BossRepository;
import com.example.marketback.repository.boss.bossReview.BossReviewRepository;
import com.example.marketback.repository.jpa.community.CommunityBoardRepository;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.repository.near.NearRepository;
import com.example.marketback.response.BossMapResponse;
import com.example.marketback.response.NearPageResponse;
import com.example.marketback.response.NearReviewResponse;
import com.example.marketback.response.NoBossMapResponse;
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
    private CommunityBoardRepository communityBoardRepository;

    @Autowired
    private NearRepository nearRepository;

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

    @Override
    public List<NoBossMapResponse> communityMapList() {
        List<Near> nearList = nearRepository.findAll();

        List<NoBossMapResponse> responses = new ArrayList<>();

        for(Near near : nearList){
            int maxCount = near.getReviewCount() > near.getCommunityCount() ? near.getReviewCount() : near.getCommunityCount();
            responses.add(new NoBossMapResponse(near.getNearNo(), near.getPlaceName(), near.getLat(), near.getLng(), near.getStoreRegion(), near.getCategory(), maxCount));
        }
        return responses;
    }

    @Override
    public List<NearReviewResponse> communityBoard() {
        List<Near> nearEntity = nearRepository.findAll();
        List<NearReviewResponse> responses = new ArrayList<>();
        System.out.println(nearEntity.size());

        for(Near near : nearEntity) {
            if(near.getReviewCount() == 0 && near.getCommunityCount() == 0){
                responses.add(null);
            }else {
                List<CommunityBoard> communityBoardList = communityBoardRepository.findTopByBossNoOrderByIdDesc(near.getNearNo());
                System.out.println("test: "+ communityBoardList.size());
                responses.add(new NearReviewResponse(communityBoardList.get(0).getMember().getName(), communityBoardList.get(0).getMember().getRegion(), communityBoardList.get(0).getContent(), communityBoardList.get(0).getMember().getProfileImg(), communityBoardList.get(0).getUsedSubject()));
            }
        }
        return responses;
    }

    @Override
    public NearPageResponse showNearPage(Long nearNo) {
        Near nearEntity = nearRepository.findByNearNo(nearNo);

        return new NearPageResponse(nearEntity.getNearNo(), nearEntity.getPlaceName(), nearEntity.getCategory(), nearEntity.getPhoneNumber(), nearEntity.getMarketHomePage(), nearEntity.getAddress(), nearEntity.getStoreRegion(), nearEntity.getLat(), nearEntity.getLng(), nearEntity.getReviewCount(), nearEntity.getCommunityCount());
    }
}