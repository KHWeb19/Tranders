package com.example.marketback.controller.boss;

import com.example.marketback.service.member.BossService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/boss")
public class BossController {

    @Autowired
    private BossService bossService;

    @PostMapping("/checkMember")
    public boolean checkMember(@RequestBody Map<String, String> map){
        System.out.println(map.get("id"));

        return bossService.checkBossMember(map.get("id"));
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> bossRegister(@RequestParam("imgFile") MultipartFile imgFile,
                                       @RequestParam("id") String id,
                                       @RequestParam("name") String name,
                                       @RequestParam("region") String region,
                                       @RequestParam("category") String category){

        log.info("bossRegister");
        log.info("imgFile" + imgFile);

        try{
            log.info("requestUploadFile() - Make file: " + imgFile.getOriginalFilename());

            FileOutputStream file = new FileOutputStream("../marketfront/src/assets/bossProfile/" + id + "_" + imgFile.getOriginalFilename());

            String fileSrc = name + "_" + imgFile.getOriginalFilename();

            System.out.println(fileSrc+", "+name+", "+region);
            file.write(imgFile.getBytes());
            file.close();

            bossService.register(fileSrc, id, name, region, category);
        } catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
