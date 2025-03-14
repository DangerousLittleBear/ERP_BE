package com.example.demo.repository;

import com.example.demo.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByProductCode(String productCode);
    
    List<Stock> findByProductNameContaining(String productName);
    
    List<Stock> findByCategory(String category);
    
    List<Stock> findByWarehouseId(Long warehouseId);
    
    List<Stock> findByQuantityLessThan(Integer threshold);
}
