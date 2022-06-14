package com.example.marketback.controller.productBoard;

import com.example.marketback.entity.productBoard.Product;
import com.example.marketback.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/register")
    public void productRegister (@Validated @RequestBody Product product) {
        log.info("productRegister()");

        service.register(product);
    }

    @GetMapping("/list")
    public List<Product> productList() {
        log.info("productList()");

        return service.list();
    }

    @GetMapping("/{productNo}")
    public Product productRead (@PathVariable("productNo") Integer productNo) {
        log.info("productRead()");

        return service.read(productNo);
    }

    @PutMapping("/{productNo}")
    public Product productModify (@PathVariable("productNo") Integer productNo, @RequestBody Product product) {
        log.info("productModify(): " + product);

        product.setProductNo(Long.valueOf(productNo));
        service.modify(product);

        return product;
    }

    @DeleteMapping("/{productNo}")
    public void productRemove (@PathVariable("productNo") Integer productNo) {
        log.info("productRemove()");

        service.remove(productNo);
    }
}
