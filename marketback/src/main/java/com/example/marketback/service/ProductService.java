package com.example.marketback.service;

import com.example.marketback.entity.productBoard.ProductBoard;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    public void register(ProductBoard productBoard, Long memberNo);
    public List<ProductBoard> list();
    public ProductBoard read(Integer productNo);
    public void modify(ProductBoard productBoard);
    public void remove(Long productNo);
}
