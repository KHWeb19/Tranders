package com.example.marketback.service;
import com.example.marketback.entity.productBoard.Product;
import com.example.marketback.repository.productBoard.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repository;

    @Override
    public void register(Product product) {
        repository.save(product);
    }

    @Override
    public List<Product> list() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "productNo"));
    }

    @Override
    public Product read(Integer productNo) {
        Optional<Product> maybeReadProduct = repository.findById(Long.valueOf(productNo));

        if (maybeReadProduct.equals(Optional.empty())) {
            return null;
        }

        return maybeReadProduct.get();
    }

    @Override
    public void modify(Product product) {
        repository.save(product);
    }
    @Override
    public void remove(Integer productNo) {
        repository.deleteById(Long.valueOf(productNo));
    }
}
