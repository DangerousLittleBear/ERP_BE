package com.example.demo.controller;

import com.example.demo.model.Stock;
import com.example.demo.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        return stockService.getStockById(id)
                .map(stock -> new ResponseEntity<>(stock, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/code/{productCode}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Stock> getStockByProductCode(@PathVariable String productCode) {
        return stockService.getStockByProductCode(productCode)
                .map(stock -> new ResponseEntity<>(stock, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{productName}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<List<Stock>> getStocksByProductName(@PathVariable String productName) {
        List<Stock> stocks = stockService.getStocksByProductName(productName);
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<List<Stock>> getStocksByCategory(@PathVariable String category) {
        List<Stock> stocks = stockService.getStocksByCategory(category);
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @GetMapping("/warehouse/{warehouseId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<List<Stock>> getStocksByWarehouse(@PathVariable Long warehouseId) {
        List<Stock> stocks = stockService.getStocksByWarehouse(warehouseId);
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @GetMapping("/low-stock")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<Stock>> getLowStocks(@RequestParam(defaultValue = "10") Integer threshold) {
        List<Stock> stocks = stockService.getLowStocks(threshold);
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        Stock savedStock = stockService.saveStock(stock);
        return new ResponseEntity<>(savedStock, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody Stock stockDetails) {
        try {
            Stock updatedStock = stockService.updateStock(id, stockDetails);
            return new ResponseEntity<>(updatedStock, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/quantity")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Stock> updateStockQuantity(@PathVariable Long id, @RequestBody Integer quantity) {
        try {
            Stock updatedStock = stockService.updateStockQuantity(id, quantity);
            return new ResponseEntity<>(updatedStock, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteStock(@PathVariable Long id) {
        try {
            stockService.deleteStock(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
