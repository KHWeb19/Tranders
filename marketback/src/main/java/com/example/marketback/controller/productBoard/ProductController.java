package com.example.marketback.controller.productBoard;

import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.request.KeyWordRequest;
import com.example.marketback.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/register/{memberNo}")
    public void productRegister(@Validated @RequestBody ProductBoard productBoard, @PathVariable("memberNo") Long memberNo) {
        log.info("productRegister()" + productBoard);

        productService.register(productBoard, memberNo);
    }

    @GetMapping("/list")
    public List<ProductBoard> productList() {
        log.info("productList()");

        return productService.list();
    }

    @GetMapping("/{productNo}")
    public ProductBoard productRead(@PathVariable("productNo") Integer productNo) {
        log.info("productRead()");

        return productService.read(productNo);
    }

    @PutMapping("/{memberNo}/{productNo}")
    public ProductBoard productModify(@PathVariable("productNo") Integer productNo, @RequestBody ProductBoard productBoard, @PathVariable("memberNo") Long memberNo) {
        log.info("productModify(): " + productBoard);

        productBoard.setProductNo(Long.valueOf(productNo));
        productService.modify(productBoard, memberNo);

        return productBoard;
    }

    @DeleteMapping("/{productNo}")
    public void productRemove(@PathVariable("productNo") Long productNo) {
        log.info("productRemove()");

        productService.remove(productNo);
    }

    @ResponseBody
    @PostMapping("/uploadImg")
    public String requestUploadFile (@RequestParam("fileList") List<MultipartFile> fileList) {
        try {
            for (MultipartFile multipartFile: fileList) {
                log.info("requestUploadFile() - Make file:" + multipartFile.getOriginalFilename());

                FileOutputStream writer = new FileOutputStream("../marketfront/src/assets/pImage/" + multipartFile.getOriginalFilename());

                writer.write(multipartFile.getBytes());
                writer.close();
            }
        } catch (Exception e) {
            return "Upload Fail!";
        }

        log.info("requestUploadFile(): Success!!");
        return "Upload Success";
    }

    @PostMapping("/search")
    public List<ProductBoard> productBoardSearchList(@RequestBody KeyWordRequest keyWord) {
        log.info("ProductBoardSearchList()");
        String word = keyWord.getKeyWord();

        return productService.searchList(word);
    }
}
