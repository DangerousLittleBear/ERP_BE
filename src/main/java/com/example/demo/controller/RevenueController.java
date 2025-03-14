package com.example.demo.controller;

import com.example.demo.model.Revenue;
import com.example.demo.service.RevenueService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/revenues")
public class RevenueController {
    private final RevenueService revenueService;

    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<Revenue>> getAllRevenues() {
        List<Revenue> revenues = revenueService.getAllRevenues();
        return new ResponseEntity<>(revenues, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Revenue> getRevenueById(@PathVariable Long id) {
        return revenueService.getRevenueById(id)
                .map(revenue -> new ResponseEntity<>(revenue, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/date-range")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<Revenue>> getRevenuesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Revenue> revenues = revenueService.getRevenuesByDateRange(startDate, endDate);
        return new ResponseEntity<>(revenues, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<Revenue>> getRevenuesByCategory(@PathVariable String category) {
        List<Revenue> revenues = revenueService.getRevenuesByCategory(category);
        return new ResponseEntity<>(revenues, HttpStatus.OK);
    }

    @GetMapping("/source/{source}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<Revenue>> getRevenuesBySource(@PathVariable String source) {
        List<Revenue> revenues = revenueService.getRevenuesBySource(source);
        return new ResponseEntity<>(revenues, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Revenue> createRevenue(@RequestBody Revenue revenue) {
        Revenue savedRevenue = revenueService.saveRevenue(revenue);
        return new ResponseEntity<>(savedRevenue, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Revenue> updateRevenue(@PathVariable Long id, @RequestBody Revenue revenueDetails) {
        try {
            Revenue updatedRevenue = revenueService.updateRevenue(id, revenueDetails);
            return new ResponseEntity<>(updatedRevenue, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteRevenue(@PathVariable Long id) {
        try {
            revenueService.deleteRevenue(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
