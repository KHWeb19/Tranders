package com.example.marketback.controller.productBoard.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String title;
    private Integer price;
    private String content;

    public ProductRequest (String title, Integer price, String content) {
        this.title = title;
        this.price = price;
        this.content = content;
    }
}