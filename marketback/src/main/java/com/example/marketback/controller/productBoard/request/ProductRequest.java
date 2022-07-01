package com.example.marketback.controller.productBoard.request;

import lombok.Data;


@Data
public class ProductRequest {
    private Long productNo;
    private String productImage;
    private String title;
    private Integer price;
    private String content;

    public ProductRequest (Long productNo, String productImage, String title, Integer price, String content) {
        this.productNo = productNo;
        this.productImage = productImage;
        this.title = title;
        this.price = price;
        this.content = content;
    }
}