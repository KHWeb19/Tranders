package com.example.marketback.sevice.productBoardService;

import com.example.marketback.entity.productEntity.ProductBoard;

import java.util.List;

public interface ProductBoardService {
    public void register (ProductBoard productBoard);
    public List<ProductBoard> list ();
    public JpaBoard read (Integer productNo);
    public void modify (ProductBoard productBoard);
    public void remove (Integer productNo);
}
}
