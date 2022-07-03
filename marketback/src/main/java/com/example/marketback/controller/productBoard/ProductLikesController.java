package com.example.marketback.controller.productBoard;

import com.example.marketback.entity.productBoard.ProductLikes;
import com.example.marketback.service.productBoard.ProductLikesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/productLikes")
public class ProductLikesController {

    @Autowired
    private ProductLikesService productLikesService;

    @PostMapping("/{productNo}/{memberNo}")
    public boolean productLikes(@PathVariable("productNo") Long productNo,@PathVariable("memberNo") Long memberNo, @Validated @RequestBody ProductLikes productLikes) {
        log.info("productLikes()");

        return productLikesService.register(productNo, memberNo, productLikes);
    }

    @GetMapping("/{productNo}/{memberNo}")
    public ProductLikes productLikesRead(@PathVariable("productNo") Long productNo, @PathVariable("memberNo") Long memberNo) {
        log.info("productLikesRead()");

        return productLikesService.productLikes(productNo, memberNo);

    }

    @GetMapping("/list/{productNo}")
    public List<ProductLikes> productLikesList(@PathVariable("productNo") Long productNo) {
        log.info("productLikesList()");

        return productLikesService.list(productNo);
    }

    @GetMapping("/list/my/{memberNo}")
    public List<ProductLikes> productMyLikesList(@PathVariable("memberNo") Long memberNo) {
        log.info("productMyLikesList()");

        return productLikesService.myLikes(memberNo);
    }
}
