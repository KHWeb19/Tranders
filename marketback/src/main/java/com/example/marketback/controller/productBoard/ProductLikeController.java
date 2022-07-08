package com.example.marketback.controller.productBoard;

import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.entity.productBoard.ProductLike;
import com.example.marketback.service.productBoard.ProductLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/productLike")
public class ProductLikeController {

    @Autowired
    private ProductLikeService productLikeService;

    @PostMapping("/{productNo}/{memberNo}")
    public boolean likes(@PathVariable("productNo") Long productNo, @PathVariable("memberNo") Long memberNo, @Validated @RequestBody ProductLike productLike) {
        log.info("likes()");
        return productLikeService.register(productNo, memberNo, productLike);
    }

    @GetMapping("/{productNo}/{memberNo}")
    public ProductLike likesRead(@PathVariable("productNo") Long productNo, @PathVariable("memberNo") Long memberNo) {
        log.info("likesRead()");

        return productLikeService.likes(productNo, memberNo);
    }

    @GetMapping("/list/{productNo}")
    public List<ProductLike> likesList(@PathVariable("productNo") Long productNo) {
        log.info("likesList()");

        return productLikeService.list(productNo);
    }

    @GetMapping("/list/my/{memberNo}")
    public List<ProductLike> myLikesList(@PathVariable("memberNo") Long memberNo) {
        log.info("likesList()");

        return productLikeService.myLikes(memberNo);
    }
}
