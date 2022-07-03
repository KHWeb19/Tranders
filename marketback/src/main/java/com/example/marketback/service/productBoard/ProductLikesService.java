package com.example.marketback.service.productBoard;

import com.example.marketback.entity.productBoard.ProductLikes;

import java.util.List;

public interface ProductLikesService {
    public boolean register (Long productNo, Long memberNo, ProductLikes productLikes);
    ProductLikes productLikes(Long productNo, Long memberNo);
    List<ProductLikes> list (Long productNo);
    List<ProductLikes> myLikes (Long memberNo);
}
