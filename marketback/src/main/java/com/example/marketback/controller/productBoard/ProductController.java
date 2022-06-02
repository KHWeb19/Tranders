package com.example.marketback.controller.productBoard;

import com.example.marketback.entity.productEntity.ProductBoard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/62th/board")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductBoardRepository productService;
    @PostMapping("/productRegister")
    public String productRegister (@Validated @RequestBody ProductBoard productBoard){
        log.info("productRegister" + productBoard);

        productService.productRegister(productBoard);
    }
    @GetMapping("/productList")
    public List<ProductBoard> productList (){
        log.info("productList");

        return productService.list();
    }
    @GetMapping("/{productNo}")
    public ProductBoard productRead (
            @PathVariable("productNo") Integer productNo) {
        log.info("productRead");

            return productService.read(productNo);
    }
    @PutMapping("/{productNo}")
    public ProductBoard ProductModify (
            @PathVariable("productNo") Integer productNo,
            @RequestBody ProductBoard productBoard) {
        log.info("jpaBoardModify(): " + ProductBoard);

        ProductBoard.setProductNo(Long.valueOf(productNo));
        productService.modify(ProductBoard);

        return ProductBoard;
    }
    @DeleteMapping("/{productNo}")
    public void productDelete (
            @PathVariable("productNo") Integer productNo) {
        log.info("productDelete()");

        productService.remove(productNo);
    }
}
