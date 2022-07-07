package com.example.marketback.controller.search;

import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.request.KeyWordRequest;
import com.example.marketback.service.jpa.community.CommunityBoardService;
import com.example.marketback.service.productBoard.ProductService;
import com.example.marketback.service.search.SearchKeyWordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/search")
public class SearchController {


    @Autowired
    private CommunityBoardService communityBoardService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SearchKeyWordService searchKeyWordService;

    @PostMapping("/comm")
    public List<CommunityBoard> CommunityBoardSearchList (@RequestBody KeyWordRequest keyWord) {
        log.info("CommunityBoardSearchList()");
        String word = keyWord.getKeyWord();

        return communityBoardService.searchList(word);
    }


    @PostMapping("/product")
    public List<ProductBoard> productBoardSearchList(@RequestBody KeyWordRequest keyWord) {
        log.info("ProductBoardSearchList()");
        String word = keyWord.getKeyWord();

        return productService.searchList(word);
    }

    @PostMapping("/all/product")
    public List<ProductBoard> searchProductBoard(@RequestBody KeyWordRequest keyWordRequest){
        log.info("searchProductBoard");

        return searchKeyWordService.searchProduct(keyWordRequest);
    }
}
