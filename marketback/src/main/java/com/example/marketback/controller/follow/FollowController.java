package com.example.marketback.controller.follow;

import com.example.marketback.entity.follow.Follow;
import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.service.follow.FollowService;
import com.example.marketback.service.productBoard.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/follow")
public class FollowController {
    @Autowired
    private FollowService followService;

    @PostMapping("/{loginNo}/{memberNo}")
    public boolean following(@PathVariable("loginNo") Long my, @PathVariable("memberNo") Long your, @Validated @RequestBody Follow follow) {
        log.info("following()");
        return followService.register(my, your, follow);
    }

    @GetMapping("/on/{loginNo}/{memberNo}")
    public boolean onFollow(@PathVariable("loginNo") Long my, @PathVariable("memberNo") Long your) {
        log.info("onFollow()");
        return followService.onFollow(my, your);
    }

    @GetMapping("/list/{memberNo}")
    public List<Follow> followList(@PathVariable("memberNo") Long memberNo) {
        log.info("followList()");

        return followService.followList(memberNo);
    }

    @GetMapping("/boardList/{memberNo}")
    public List<ProductBoard> followBoardList(@PathVariable("memberNo") Long memberNo) {
        log.info("followBoardList()");

        return followService.followBoardList(memberNo);
    }
}
