package com.example.marketback.service;

import com.example.marketback.entity.productBoard.Product;

import java.util.List;

public interface ProductService {
    public void register (Product product);
    public List<Product> list();
    public Product read (Integer productNo);
    public void modify (Product product);
    public void remove (Integer productNo);
}
