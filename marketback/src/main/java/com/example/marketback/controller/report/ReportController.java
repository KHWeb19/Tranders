package com.example.marketback.controller.report;

import com.example.marketback.request.ReportRequest;
import com.example.marketback.service.report.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/saveReport")
    public void saveReport(@RequestBody ReportRequest reportRequest){
        log.info("saveReport "+reportRequest.getContent()+", "+reportRequest.getBanCheck());

        reportService.saveReport(reportRequest);
    }

    @PostMapping("/saveImgText")
    public ResponseEntity<Boolean> saveImgText(@RequestParam("fileList") List<MultipartFile> imgFile,
                            @RequestParam("category1") String category1,
                            @RequestParam("category2") String category2,
                            @RequestParam("content") String content,
                            @RequestParam("banCheck") Boolean banCheck,
                            @RequestParam("productNo") Long productNo,
                            @RequestParam("toId") String toId,
                            @RequestParam("fromId") String fromId){
        log.info("saveImgText :" + category1);

        List<String> fileName = new ArrayList<>();
        ReportRequest reportRequest = new ReportRequest(category1, category2, content, banCheck, productNo, toId, fromId);

        if(imgFile.size() != 0) {
            UUID uuid = UUID.randomUUID();
            try {
                for (MultipartFile files : imgFile) {
                    log.info("requestUploadFile() - Make file: " + files.getOriginalFilename());

                    FileOutputStream file = new FileOutputStream("../marketfront/src/assets/report/" + toId + "_" + uuid+ "_" + files.getOriginalFilename());

                    String fileSrc = toId + "_" + uuid+ "_" + files.getOriginalFilename();

                    fileName.add(fileSrc);
                    log.info(fileSrc);
                    file.write(files.getBytes());
                    file.close();
                }

            } catch (Exception e) {
                log.info("에러");
                return new ResponseEntity<>(false, HttpStatus.OK);
            }

            reportService.saveReportImg(fileName, reportRequest);
        }else {

            reportService.saveReport(reportRequest);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
