package com.example.marketback.service.search;

import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.request.KeyWordRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SearchKeyWordService {
    List<ProductBoard> searchProduct(KeyWordRequest keyWordRequest);

    List<CommunityBoard> searchAllComm(KeyWordRequest keyWordRequest);

}
