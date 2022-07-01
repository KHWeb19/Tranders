package com.example.marketback.controller.near;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.response.BossMapResponse;
import com.example.marketback.response.NearPageResponse;
import com.example.marketback.response.NearReviewResponse;
import com.example.marketback.response.NoBossMapResponse;
import com.example.marketback.service.near.NearService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/near")
public class NearController {

    @Autowired
    private NearService nearService;

    @PostMapping("/showMap")
    public List<BossMapResponse> showMap(){
        log.info("showMap");

        return nearService.showMap();
    }

    @PostMapping("/addMap")
    public void addMap(){ // 나중에 지우기, 마커 생성을 위해서

        nearService.test();
    }

    @PostMapping("/readBossPage/{bossNo}")
    public Boss readBossPage(@PathVariable Long bossNo){
        log.info("readBossPage: "+ bossNo);

        return nearService.returnBoss(bossNo);
    }

    @PostMapping("/review")
    public List<NearReviewResponse> nearReview(){
        log.info("NearReviewResponse");

       return nearService.nearReview();
    }

    @PostMapping("/communityMap")
    public List<NoBossMapResponse> nearCommunityMap() {
        log.info("nearCommunityMap");

        return nearService.communityMapList();
    }

    @PostMapping("/communityBoard")
    public List<NearReviewResponse> nearCommunityBoard(){
        log.info("nearCommunityBoard");

        return nearService.communityBoard();
    }

    @PostMapping("/{nearNo}")
    public NearPageResponse showNearPage(@PathVariable Long nearNo){
        log.info("showNearPage: "+ nearNo);

        return nearService.showNearPage(nearNo);
    }
}
