package com.example.marketback.service.productBoard;

import com.example.marketback.entity.productBoard.ProductBoard;

import java.util.List;

public interface ProductService {
    public void register(ProductBoard productBoard, Long memberNo);
    public List<ProductBoard> list();
    public List<ProductBoard> memberOfProductList(Long memberNo);
    public List<ProductBoard> regionOfProductList(Long memberNo);
    public ProductBoard read(Integer productNo);
    public void modify(ProductBoard productBoard, Long memberNo);
    public void remove(Long productNo);
    public List<ProductBoard> searchList(String searchWord);
    public List<ProductBoard> productListOfMember(Long memberNo);
    public List<ProductBoard> buyerList(Long memberNo);

}

