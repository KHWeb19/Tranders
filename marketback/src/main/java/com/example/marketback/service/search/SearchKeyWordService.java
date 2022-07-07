package com.example.marketback.service.search;

import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.request.KeyWordRequest;

import java.util.List;

public interface SearchKeyWordService {
    List<ProductBoard> searchProduct(KeyWordRequest keyWordRequest);
}
