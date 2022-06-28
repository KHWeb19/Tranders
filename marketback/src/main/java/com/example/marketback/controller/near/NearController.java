package com.example.marketback.controller.near;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.response.BossMapResponse;
import com.example.marketback.service.near.NearService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void addMap(){

        nearService.test();
    }
}
