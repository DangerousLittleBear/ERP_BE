package com.example.demo.service;

import com.example.demo.model.Stock;
import com.example.demo.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Optional<Stock> getStockById(Long id) {
        return stockRepository.findById(id);
    }

    public Optional<Stock> getStockByProductCode(String productCode) {
        return stockRepository.findByProductCode(productCode);
    }

    public List<Stock> getStocksByProductName(String productName) {
        return stockRepository.findByProductNameContaining(productName);
    }

    public List<Stock> getStocksByCategory(String category) {
        return stockRepository.findByCategory(category);
    }

    public List<Stock> getStocksByWarehouse(Long warehouseId) {
        return stockRepository.findByWarehouseId(warehouseId);
    }

    public List<Stock> getLowStocks(Integer threshold) {
        return stockRepository.findByQuantityLessThan(threshold);
    }

    @Transactional
    public Stock saveStock(Stock stock) {
        stock.setLastUpdated(LocalDateTime.now());
        return stockRepository.save(stock);
    }

    @Transactional
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    @Transactional
    public Stock updateStock(Long id, Stock stockDetails) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found with id: " + id));

        stock.setProductCode(stockDetails.getProductCode());
        stock.setProductName(stockDetails.getProductName());
        stock.setDescription(stockDetails.getDescription());
        stock.setCategory(stockDetails.getCategory());
        stock.setQuantity(stockDetails.getQuantity());
        stock.setUnitPrice(stockDetails.getUnitPrice());
        stock.setPurchasePrice(stockDetails.getPurchasePrice());
        stock.setWarehouse(stockDetails.getWarehouse());
        stock.setLastUpdated(LocalDateTime.now());

        return stockRepository.save(stock);
    }

    @Transactional
    public Stock updateStockQuantity(Long id, Integer quantity) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found with id: " + id));

        stock.setQuantity(quantity);
        stock.setLastUpdated(LocalDateTime.now());
        return stockRepository.save(stock);
    }
}
