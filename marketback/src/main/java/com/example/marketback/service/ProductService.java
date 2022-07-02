package com.example.marketback.service;

import com.example.marketback.entity.productBoard.ProductBoard;

import java.util.List;

public interface ProductService {
    public void register(ProductBoard productBoard, Long memberNo);
    public List<ProductBoard> list();
    public ProductBoard read(Integer productNo);
    public void modify(ProductBoard productBoard, Long memberNo);
    public void remove(Long productNo);
    public List<ProductBoard> searchList(String searchWord);
}

