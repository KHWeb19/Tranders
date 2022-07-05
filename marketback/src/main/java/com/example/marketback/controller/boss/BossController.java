package com.example.marketback.controller.boss;

import com.example.marketback.entity.boss.Boss;
import com.example.marketback.entity.boss.BossPrice;
import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.review.BossReview;
import com.example.marketback.repository.boss.bossReview.BossReviewRepository;
import com.example.marketback.request.BossMarketInfoRequest;
import com.example.marketback.response.*;
import com.example.marketback.service.boss.BossService;
import com.example.marketback.service.boss.review.BossReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/boss")
public class BossController {

    @Autowired
    private BossService bossService;

    @Autowired
    private BossReviewService bossReviewService;

    @Autowired
    private BossReviewRepository bossReviewRepository;


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

            FileOutputStream file = new FileOutputStream("../marketfront/src/assets/bossProfile/front/" + id + "_" + imgFile.getOriginalFilename());

            String fileSrc = id + "_" + imgFile.getOriginalFilename();

            System.out.println(fileSrc+", "+name+", "+region);
            file.write(imgFile.getBytes());
            file.close();

            bossService.register(fileSrc, id, name, region, category);
        } catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/pageView")
    public Boss bossView(@RequestBody Map<String, Long> memberNo){
        log.info("bossView");
        log.info(memberNo.get("memberNo")+"");

        return bossService.getPage(memberNo.get("memberNo"));
    }

    @PostMapping("/saveMarketInfo")
    public void saveMarketInfo (@RequestBody BossMarketInfoRequest bossMarketInfoRequest){
        log.info("marketInfo" + bossMarketInfoRequest.getId());

        bossService.saveMarketInfo(bossMarketInfoRequest);
    }

    @PostMapping("/modifyProfile")
    public ResponseEntity<Boolean> modifyProfile (@RequestParam("imgFile") MultipartFile imgFile,
                                                @RequestParam("id") String id){

        try{
            log.info("requestUploadFile() - Make file: " + imgFile.getOriginalFilename());

            FileOutputStream file = new FileOutputStream("../marketfront/src/assets/bossProfile/front/" + id + "_" + imgFile.getOriginalFilename());

            String fileSrc = id + "_" + imgFile.getOriginalFilename();

            file.write(imgFile.getBytes());
            file.close();

            bossService.modifyProfile(fileSrc, id);
        } catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/saveBackImg")
    public ResponseEntity<Boolean> saveBackProfile (@RequestParam("fileList") List<MultipartFile> imgFile,
                                                  @RequestParam("id") String id,
                                                  @RequestParam("name") String name){

        log.info("saveBackProfile");

        List<String> fileName = new ArrayList<>();

        try{
            for(MultipartFile files : imgFile) {
                log.info("requestUploadFile() - Make file: " + files.getOriginalFilename());

                FileOutputStream file = new FileOutputStream("../marketfront/src/assets/bossProfile/back/" + id + "_" + name +"_" + files.getOriginalFilename());

                String fileSrc = id + "_" + name +"_" +  files.getOriginalFilename();

                fileName.add(fileSrc);
                log.info(fileSrc);
                file.write(files.getBytes());
                file.close();
            }

        } catch (Exception e){
            log.info("에러");
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

        bossService.saveBackImg(id, name, fileName);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/getBackProfile")
    public List<BossBackProfileImg> getBackProfile(@RequestBody Map<String, Long> map){
        log.info("getBackProfile"+ map.get("bossNo"));

        return bossService.getBackProfile(map.get("bossNo"));
    }

    @PostMapping("/addPrice/{bossNo}")
    public void addPrice(@PathVariable Long bossNo, @RequestBody BossPrice bossPrice){
        log.info("addPrice" + bossNo);

        bossService.addPrice(bossPrice, bossNo);
    }

    @PostMapping("/getMenu/{id}")
    public List<BossPriceMenuResponse> getMenu(@PathVariable String id){
        log.info("get Menu");

        return bossService.getMenu(id);
    }

    @PostMapping("/modifyMenu")
    public void modifyMenu(@RequestBody BossPrice bossPrice){
        log.info("modifyMenu"+ bossPrice.getBossPriceNo());

        bossService.modifyMenu(bossPrice);
    }

    @DeleteMapping("/deleteMenu/{menuNo}")
    public void deleteMenu(@PathVariable Long menuNo){
        log.info("deleteMenu" + menuNo);
        bossService.deleteMenu(menuNo);
    }

    @PostMapping("/modifyMarketInfo")
    public void modifyMarketInfo (@RequestBody BossMarketInfoRequest bossMarketInfoRequest){
        log.info("modifyMarketInfo" + bossMarketInfoRequest.getId());

        bossService.saveMarketInfo(bossMarketInfoRequest);
    }

    @PostMapping("/registerReview")
    public ResponseEntity<Boolean> registerReview(@RequestParam("fileList") List<MultipartFile> imgFile,
                               @RequestParam("id") String id,
                               @RequestParam("name") String name,
                               @RequestParam("content") String content,
                               @RequestParam("bossNo") Long bossNo,
                               @RequestParam("nearNo") Long nearNo,
                               @RequestParam("state") String state){

        log.info("registerReview");

        List<String> fileName = new ArrayList<>();
        Long num;
        if(nearNo == 0) num = bossNo;
        else num = nearNo;
        UUID uuid = UUID.randomUUID();
        try{
            for(MultipartFile files : imgFile) {
                log.info("requestUploadFile() - Make file: " + files.getOriginalFilename());

                FileOutputStream file = new FileOutputStream("../marketfront/src/assets/bossReview/" + id +"_"+ num + "_" + uuid+ "_" + files.getOriginalFilename());

                String fileSrc = id +"_"+ num + "_" + uuid+ "_" + files.getOriginalFilename();

                fileName.add(fileSrc);
                log.info(fileSrc);
                file.write(files.getBytes());
                file.close();
            }

            bossReviewService.saveReview(fileName, id, name, content, bossNo, nearNo, state);

        } catch (Exception e){
            log.info("에러");
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/registerNoImgReview")
    public void registerNoImgReview(@RequestParam("id") String id,
                                    @RequestParam("name") String name,
                                    @RequestParam("content") String content,
                                    @RequestParam("bossNo") Long bossNo,
                                    @RequestParam("nearNo") Long nearNo,
                                    @RequestParam("state") String state){

        log.info("registerNoImgReview");

        bossReviewService.saveNoImgReview(id, name, content, bossNo, nearNo, state);
    }

    @PostMapping("/modifyImgReview")
    public ResponseEntity<Boolean> modifyImgReview(@RequestParam("fileList") List<MultipartFile> imgFile,
                             @RequestParam("id") String id,
                             @RequestParam("content") String content,
                             @RequestParam("state") String state,
                             @RequestParam("reviewNo") Long reviewNo,
                             @RequestParam("deleteImg") List<String> deleteImg){

        log.info("modifyReview" + deleteImg.get(0));

        List<String> fileName = new ArrayList<>();

        UUID uuid = UUID.randomUUID();

        try{
            for(MultipartFile files : imgFile) {
                log.info("requestUploadFile() - Make file: " + files.getOriginalFilename());

                FileOutputStream file = new FileOutputStream("../marketfront/src/assets/bossReview/" + id + "_" + uuid+ "_" + files.getOriginalFilename());

                String fileSrc = id + "_" + uuid+ "_" + files.getOriginalFilename();

                fileName.add(fileSrc);
                log.info(fileSrc);
                file.write(files.getBytes());
                file.close();
            }

            bossReviewService.modifyImgReview(fileName, id, content, reviewNo, state, deleteImg);

        } catch (Exception e){
            log.info("에러");
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/modifyReview")
    public void modifyReview (@RequestParam("content") String content,
                              @RequestParam("state") String state,
                              @RequestParam("reviewNo") Long reviewNo,
                              @RequestParam("deleteImg") List<String> deleteImg){

        log.info("modifyReview");

        bossReviewService.modifyReview(content, state, reviewNo, deleteImg);
    }

    @PostMapping("/review")
    public List<ReviewResponse> reviewList(@RequestBody Map<String, String> num){
        log.info("boss!!!!reviewList: " + num.get("num"));
        return bossReviewService.getReview(num.get("num"));
    }

    @PostMapping("/reviewImg")
    public List<List<BossReviewImageResponse>> reviewImg(@RequestBody Map<String, String> num){
        log.info("reviewList: " + num.get("num"));
        List<BossReview> bossReviewEntity;

        if(num.get("num").charAt(0) == '0' && num.get("num").charAt(1) == '0') {
            bossReviewEntity = bossReviewRepository.findByNearNo(Long.valueOf(num.get("num")));
        }else{
            bossReviewEntity = bossReviewRepository.findByBossNo(Long.valueOf(num.get("num")));
        }

        return bossReviewService.getReviewImg(bossReviewEntity, num.get("num"));
    }

    @PostMapping("/comm")
    public List<CommunityBoardListResponse> commList(@RequestBody Map<String, String> num){
        log.info("reviewList: " + num.get("num"));

        return bossReviewService.getComm(num.get("num"));
    }

    @PostMapping("/deleteReview/{reviewNo}/{entityNo}")
    public void deleteReview(@PathVariable Long reviewNo, @PathVariable String entityNo){
        log.info("deleteReview" + entityNo +"!!!!!!!!!!!!!!!!!!!!!!!");

        bossReviewService.deleteReview(reviewNo, entityNo);
    }
}
