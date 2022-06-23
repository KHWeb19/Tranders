package com.example.marketback.service.member;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.boss.BossImage;
import com.example.marketback.entity.boss.BossPrice;
import com.example.marketback.entity.member.Member;
import com.example.marketback.repository.boss.BossImgRepository;
import com.example.marketback.repository.boss.BossPriceRepository;
import com.example.marketback.repository.boss.BossRepository;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.request.BossMarketInfoRequest;
import com.example.marketback.response.BossBackProfileImg;
import com.example.marketback.response.BossPriceMenuResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class BossServiceImpl implements BossService{

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BossRepository bossRepository;

    @Autowired
    private BossImgRepository bossImgRepository;

    @Autowired
    private BossPriceRepository bossPriceRepository;

    @Override
    public boolean checkBossMember(String id) {
        Member isBossMember = memberRepository.findByMemberId(id);

        System.out.println(isBossMember.getBossAuth());

        return isBossMember.getBossAuth();
    }

    @Override
    public void register(String fileSrc, String id, String name, String region, String category) {
        Member memberEntity = memberRepository.findByMemberId(id);
        memberEntity.setBossAuth(Boolean.TRUE);
        Boss boss = new Boss(name, region, fileSrc, "address", category, memberEntity);

        bossRepository.save(boss);
    }

    @Override
    public Boss getPage(Long memberNo) {
        return bossRepository.findByMemberNo(memberNo);
    }

    @Override
    public void saveMarketInfo(BossMarketInfoRequest bossMarketInfoRequest) {
        Boss bossEntity = bossRepository.findByMemberId(bossMarketInfoRequest.getId());

        bossEntity.setPhoneNumber(bossMarketInfoRequest.getPhoneNumber());
        bossEntity.setMarketInfo(bossMarketInfoRequest.getMarketInfo());
        bossEntity.setStartTime(bossMarketInfoRequest.getStartTime());
        bossEntity.setEndTime(bossMarketInfoRequest.getEndTime());
        bossEntity.setAddress(bossMarketInfoRequest.getAddress());
        bossEntity.setMarketHomePage(bossMarketInfoRequest.getMarketHomePage());
        bossEntity.setLat(bossMarketInfoRequest.getLat());
        bossEntity.setLng(bossMarketInfoRequest.getLng());

        bossRepository.save(bossEntity);
    }

    @Override
    public void saveBackImg(String id, String name, List<String> fileName) {
        Boss boss = bossRepository.findByMemberId(id);

        for (String file : fileName){
            BossImage bossImage = new BossImage(file, boss);
            bossImgRepository.save(bossImage);
        }
    }

    @Override
    public void modifyProfile(String fileSrc, String id) {
        Boss bossEntity = bossRepository.findByMemberId(id);
        bossEntity.setProfileImg(fileSrc);

        bossRepository.save(bossEntity);
    }

    @Override
    public List<BossBackProfileImg> getBackProfile(String id) {
        List<BossImage> bossImages = bossImgRepository.findImgListByMemberId(id);

        List<BossBackProfileImg> response = new ArrayList<>();

        for (BossImage bossImage : bossImages) {
            response.add(new BossBackProfileImg(bossImage.getImageName()));
        }
        return response;
    }

    @Override
    public void addPrice(BossPrice bossPrice, Long bossNo) {
        Boss bossEntity = bossRepository.findByBossNo(bossNo);

        bossPrice.setBoss(bossEntity);
        bossPriceRepository.save(bossPrice);
    }

    @Override
    public List<BossPriceMenuResponse> getMenu(String id) {
        List<BossPrice> bossPriceEntity = bossPriceRepository.findByMemberId(id);

        List<BossPriceMenuResponse> responses = new ArrayList<>();

        for(BossPrice bossPrice : bossPriceEntity){
            responses.add(new BossPriceMenuResponse(bossPrice.getBossPriceNo(), bossPrice.getMenuName(), bossPrice.getMenuPrice(), bossPrice.getMenuInfo()));
        }

        return responses;
    }

    @Override
    public void modifyMenu(BossPrice bossPrice) {
        BossPrice bossPriceEntity = bossPriceRepository.findBossByBossId(bossPrice.getBossPriceNo());
        bossPrice.setBoss(bossPriceEntity.getBoss());

        bossPriceRepository.save(bossPrice);
    }

    @Override
    public void deleteMenu(Long menuNo) {
        bossPriceRepository.deleteById(menuNo);
    }
}
