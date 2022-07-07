package com.example.marketback.service.search;

import com.example.marketback.entity.manager.Report;
import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.repository.productBoard.ProductRepository;
import com.example.marketback.request.KeyWordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchKeyWordServiceImpl implements SearchKeyWordService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductBoard> searchProduct(KeyWordRequest keyWordRequest) {
        Pageable pageable = PageRequest.of(keyWordRequest.getPage(), 2, Sort.Direction.DESC, "regDate");

        return productRepository.findByContentContaining(keyWordRequest.getKeyWord(), pageable);
    }
}
