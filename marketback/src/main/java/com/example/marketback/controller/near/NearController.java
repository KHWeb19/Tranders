package com.example.marketback.controller.near;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.review.BossReview;
import com.example.marketback.response.BossMapResponse;
import com.example.marketback.response.NearReviewResponse;
import com.example.marketback.service.boss.review.BossReviewService;
import com.example.marketback.service.near.NearService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        //return null;
    }
}
