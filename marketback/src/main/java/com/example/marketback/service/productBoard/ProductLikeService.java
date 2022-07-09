package com.example.marketback.service.productBoard;

import com.example.marketback.entity.productBoard.ProductLike;

import java.util.List;

public interface ProductLikeService {
    public boolean register (Long productNo, Long memberNo, ProductLike productLike);
    ProductLike likes (Long productNo, Long memberNo);
    List<ProductLike> list (Long productNo);
    List<ProductLike> myLikes (Long memberNo);
}
