package com.example.demo.controller;

import com.example.demo.model.Sales;
import com.example.demo.service.SalesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/sales")
public class SalesController {
    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<List<Sales>> getAllSales() {
        List<Sales> sales = salesService.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Sales> getSalesById(@PathVariable Long id) {
        return salesService.getSalesById(id)
                .map(sales -> new ResponseEntity<>(sales, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/order/{orderNumber}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Sales> getSalesByOrderNumber(@PathVariable String orderNumber) {
        return salesService.getSalesByOrderNumber(orderNumber)
                .map(sales -> new ResponseEntity<>(sales, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/date-range")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<Sales>> getSalesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<Sales> sales = salesService.getSalesByDateRange(startDate, endDate);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/customer/{customerName}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<List<Sales>> getSalesByCustomerName(@PathVariable String customerName) {
        List<Sales> sales = salesService.getSalesByCustomerName(customerName);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<List<Sales>> getSalesByStatus(@PathVariable Sales.SalesStatus status) {
        List<Sales> sales = salesService.getSalesByStatus(status);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/salesperson/{salesPersonId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or @securityService.isCurrentUser(#salesPersonId)")
    public ResponseEntity<List<Sales>> getSalesBySalesPerson(@PathVariable Long salesPersonId) {
        List<Sales> sales = salesService.getSalesBySalesPerson(salesPersonId);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Sales> createSales(@RequestBody Sales sales) {
        Sales savedSales = salesService.saveSales(sales);
        return new ResponseEntity<>(savedSales, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Sales> updateSales(@PathVariable Long id, @RequestBody Sales salesDetails) {
        try {
            Sales updatedSales = salesService.updateSales(id, salesDetails);
            return new ResponseEntity<>(updatedSales, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<Sales> updateSalesStatus(@PathVariable Long id, @RequestBody Sales.SalesStatus status) {
        try {
            Sales updatedSales = salesService.updateSalesStatus(id, status);
            return new ResponseEntity<>(updatedSales, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteSales(@PathVariable Long id) {
        try {
            salesService.deleteSales(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
