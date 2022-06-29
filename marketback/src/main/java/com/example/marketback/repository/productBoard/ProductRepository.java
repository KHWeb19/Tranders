package com.example.marketback.repository.productBoard;

import com.example.marketback.entity.productBoard.ProductBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductBoard, Long> {

}
