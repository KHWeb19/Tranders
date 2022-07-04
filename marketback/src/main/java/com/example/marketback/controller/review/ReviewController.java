package com.example.marketback.controller.review;

import com.example.marketback.entity.review.Review;
import com.example.marketback.service.review.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/register/{memberId}/{member2Id}")
    public void reviewRegister(@RequestBody Review review, @PathVariable("memberId") String memberId, @PathVariable("member2Id") String member2Id) {
        log.info("reviewRegister()"+memberId);

        reviewService.register(review, memberId, member2Id);
    }

    @GetMapping("/list/{id}")
    public List<Review> reviewList(@PathVariable("id") String id) {
        log.info("reviewList()");

        return reviewService.list(id);
    }
}
